package com.example.demo.main.ubitec;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public final class RangeLocal<T> implements Comparable<RangeLocal> {

    private final T lowerbound;

    private final T upperbound;

    public T getLowerbound() {
        return lowerbound;
    }

    public T getUpperbound() {
        return upperbound;
    }

    private final RangeType rangeType;

    private final Class<T> typeParameterClass;

    public Class<T> getTypeParameterClass() {
        return typeParameterClass;
    }

    /**
     * Constructor is private BY DESIGN.
     * <p>
     * TODO: Change the constructor to meet your requirements.
     */
    private RangeLocal(T lowerbound, T upperbound, RangeType rangeType, Class<T> typeParameterClass) {
        this.lowerbound = lowerbound;
        this.upperbound = upperbound;
        this.rangeType = rangeType;
        this.typeParameterClass = typeParameterClass;
    }

    /**
     * Creates a new <b>closed</b> {@code Range} that includes both bounds.
     */
    public static RangeLocal of(int lowerbound, int upperbound) {
        return new RangeLocal(lowerbound, upperbound, RangeType.CLOSED, validateAndConvertType(lowerbound, upperbound));
    }

    public static RangeLocal open(Object lowerbound, Object upperbound) {
        return new RangeLocal(lowerbound, upperbound, RangeType.OPEN, validateAndConvertType(lowerbound, upperbound));
    }

    public static RangeLocal closed(Object lowerbound, Object upperbound) {
        return new RangeLocal(lowerbound, upperbound, RangeType.CLOSED, validateAndConvertType(lowerbound, upperbound));
    }

    public static RangeLocal openClosed(Object lowerbound, Object upperbound) {
        return new RangeLocal(lowerbound, upperbound, RangeType.OPEN_CLOSED, validateAndConvertType(lowerbound, upperbound));
    }

    public static RangeLocal closedOpen(Object lowerbound, Object upperbound) {
        return new RangeLocal(lowerbound, upperbound, RangeType.CLOSED_OPEN, validateAndConvertType(lowerbound, upperbound));
    }

    private static Class validateAndConvertType(Object lowerbound, Object upperbound) {
        String message = "Should not allow creating a invalid Range";
        if (lowerbound.getClass() != upperbound.getClass())
            throw new RuntimeException("Need to input only the type");
        Class inputClass = lowerbound.getClass();
        if (inputClass == Integer.class
                && new Integer(lowerbound.toString()) >= new Integer(upperbound.toString())) {
            throw new RuntimeException(message);
        } else if (inputClass == BigDecimal.class
                && new BigDecimal(lowerbound.toString()).compareTo(new BigDecimal(upperbound.toString())) >= 0) {
            throw new RuntimeException(message);
        } else if (inputClass == LocalDate.class
                && (((LocalDate) lowerbound).isAfter((LocalDate) upperbound)
                || lowerbound.equals(upperbound))) {
            // LocalDate vs Date vs LocalDateTime
            throw new RuntimeException(message);
        }
        return inputClass;
    }

    /**
     * Returns {@code true} on if the given {@code value} is contained in this
     * {@code Range}.
     */
    public boolean contains(Object value) {
        Class valueClass = Objects.nonNull(value) ? value.getClass() : null;
        if (valueClass != typeParameterClass && !rangeType.equals(RangeType.ALL))
            throw new RuntimeException("Input invalid type of the value");
        //why using switch case and if else
        if (valueClass == Integer.class) {
            Integer lowerboundInt = Objects.nonNull(lowerbound) ? Integer.parseInt(lowerbound.toString()) : null;
            Integer upperboundInt = Objects.nonNull(upperbound) ? Integer.parseInt(upperbound.toString()) : null;
            Integer valueInt = Integer.parseInt(value.toString());
            switch (rangeType) {
                case OPEN:
                    return lowerboundInt < valueInt && valueInt < upperboundInt;
                case CLOSED:
                    return lowerboundInt <= valueInt && valueInt <= upperboundInt;
                case OPEN_CLOSED:
                    return lowerboundInt < valueInt && valueInt <= upperboundInt;
                case CLOSED_OPEN:
                    return lowerboundInt <= valueInt && valueInt < upperboundInt;
                case LESS_THAN:
                    return valueInt < upperboundInt;
                case AT_LEAST:
                    return lowerboundInt <= valueInt;
                case AT_MOST:
                    return valueInt <= upperboundInt;
                case GREATER_THAN:
                    return lowerboundInt < valueInt;
                case ALL:
                    return true;
            }
        } else if (valueClass == BigDecimal.class) {
            //why using compareTo
            BigDecimal lowerboundBD = Objects.nonNull(lowerbound) ? new BigDecimal(lowerbound.toString()) : null;
            BigDecimal upperboundBD = Objects.nonNull(upperbound) ? new BigDecimal(upperbound.toString()) : null;
            BigDecimal valueBD = new BigDecimal(value.toString());
            switch (rangeType) {
                case OPEN:
                    return lowerboundBD.compareTo(valueBD) < 0 && valueBD.compareTo(upperboundBD) < 0;
                case CLOSED:
                    return lowerboundBD.compareTo(valueBD) <= 0 && valueBD.compareTo(upperboundBD) <= 0;
                case OPEN_CLOSED:
                    return lowerboundBD.compareTo(valueBD) < 0 && valueBD.compareTo(upperboundBD) <= 0;
                case CLOSED_OPEN:
                    return lowerboundBD.compareTo(valueBD) <= 0 && valueBD.compareTo(upperboundBD) < 0;
            }
        } else if (valueClass == LocalDate.class) {
            //what is LocalDate
            LocalDate lowerboundBD = (LocalDate) lowerbound;
            LocalDate upperboundBD = (LocalDate) upperbound;
            LocalDate valueBD = (LocalDate) value;
            switch (rangeType) {
                case OPEN:
                    return lowerboundBD.compareTo(valueBD) < 0 && valueBD.compareTo(upperboundBD) < 0;
                case CLOSED:
                    return lowerboundBD.compareTo(valueBD) <= 0 && valueBD.compareTo(upperboundBD) <= 0;
                case OPEN_CLOSED:
                    return lowerboundBD.compareTo(valueBD) < 0 && valueBD.compareTo(upperboundBD) <= 0;
                case CLOSED_OPEN:
                    return lowerboundBD.compareTo(valueBD) <= 0 && valueBD.compareTo(upperboundBD) < 0;
                case GREATER_THAN:
                    return lowerboundBD.compareTo(valueBD) < 0;
                case LESS_THAN:
                    return valueBD.compareTo(upperboundBD) < 0;
                case AT_LEAST:
                    return lowerboundBD.compareTo(valueBD) <= 0;
                case AT_MOST:
                    return valueBD.compareTo(upperboundBD) <= 0;
                case ALL:
                    return true;
            }
        } else if (valueClass == String.class) {
            //what is LocalDate
            String lowerboundBD = Objects.toString(lowerbound, null);
            String upperboundBD = Objects.toString(upperbound, null);
            String valueBD = Objects.toString(value, null);
            switch (rangeType) {
                case OPEN:
                    return !lowerboundBD.equals(valueBD) && !valueBD.equals(upperboundBD);
                case CLOSED:
                case ALL:
                    return true;
                case OPEN_CLOSED:
                    return !lowerboundBD.equals(valueBD);
                case CLOSED_OPEN:
                    return !valueBD.equals(upperboundBD);
            }
        } else if (valueClass == null && typeParameterClass == Object.class) {
            return true;
        }
        throw new RuntimeException("Input data invalid");
    }

    /**
     * Returns the {@code lowerbound} of this {@code Range}.
     */
    public T lowerbound() {
        return lowerbound;
    }

    /**
     * Returns the {@code upperbound} of this {@code Range}.
     */
    public T upperbound() {
        return upperbound;
    }

    public static RangeLocal lessThan(Object value) {
        return new RangeLocal(null, value, RangeType.LESS_THAN, value.getClass());
    }

    public static RangeLocal atLeast(Object value) {
        return new RangeLocal(value, null, RangeType.AT_LEAST, value.getClass());
    }

    public static RangeLocal atMost(Object value) {
        return new RangeLocal(null, value, RangeType.AT_MOST, value.getClass());
    }

    public static RangeLocal greaterThan(Object value) {
        return new RangeLocal(value, null, RangeType.GREATER_THAN, value.getClass());
    }

    public static RangeLocal all() {
        return new RangeLocal(null, null, RangeType.ALL, Object.class);
    }

    @Override
    public int compareTo(RangeLocal o) {
        return 0;
    }

    @Override
    public String toString() {
        //Why using string format, why not using str builder str buffer
        switch (rangeType) {
            case OPEN:
                return String.format(RangeType.OPEN.getSignature(), lowerbound, upperbound);
            case CLOSED:
                return String.format(RangeType.CLOSED.getSignature(), lowerbound, upperbound);
            case OPEN_CLOSED:
                return String.format(RangeType.OPEN_CLOSED.getSignature(), lowerbound, upperbound);
            case CLOSED_OPEN:
                return String.format(RangeType.CLOSED_OPEN.getSignature(), lowerbound, upperbound);
            case LESS_THAN:
                return String.format(RangeType.LESS_THAN.getSignature(), upperbound);
            case AT_LEAST:
                return String.format(RangeType.AT_LEAST.getSignature(), lowerbound);
            case AT_MOST:
                return String.format(RangeType.AT_MOST.getSignature(), upperbound);
            case GREATER_THAN:
                return String.format(RangeType.GREATER_THAN.getSignature(), lowerbound);
            case ALL:
                return RangeType.OPEN.getSignature();
        }
        return null;
    }

    public static RangeLocal parse(String value, Class classType) {
        String newValue = value.substring(1);
        newValue = newValue.substring(0, newValue.length() - 1);
        String[] split = newValue.split(", ");
        if (split.length != 2)
            throw new RuntimeException("Input the value invalid");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++)
            if (!split[i].contains("Infinitive")) {
                list.add(split[i]);
                value = value.replace(split[i], "%s");
            }
        String finalValue = value;
        RangeType rangeType1 = Stream.of(RangeType.values())
                .filter(item -> item.getSignature().equals(finalValue))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Input signature invalid"));

        if (Objects.nonNull(rangeType1)) {
            String firstValue = list.get(0);
            String secondValue = list.size() == 2 ? list.get(1) : null;
            switch (rangeType1) {
                case OPEN:
                    return RangeLocal.open(convertValue(firstValue, classType), convertValue(secondValue, classType));
                case CLOSED:
                    return RangeLocal.closed(convertValue(firstValue, classType), convertValue(secondValue, classType));
                case OPEN_CLOSED:
                    return RangeLocal.openClosed(convertValue(firstValue, classType), convertValue(secondValue, classType));
                case CLOSED_OPEN:
                    return RangeLocal.closedOpen(convertValue(firstValue, classType), convertValue(secondValue, classType));
                case LESS_THAN:
                    return RangeLocal.lessThan(convertValue(firstValue, classType));
                case AT_LEAST:
                    return RangeLocal.atLeast(convertValue(firstValue, classType));
                case AT_MOST:
                    return RangeLocal.atMost(convertValue(firstValue, classType));
                case GREATER_THAN:
                    return RangeLocal.greaterThan(convertValue(firstValue, classType));
                case ALL:
                    return RangeLocal.all();
            }
        }

        return null;
    }

    public static Object convertValue(String value, Class classType) {
        if (classType == Integer.class) {
            return Integer.valueOf(value);
        } else if (classType == BigDecimal.class) {
            return new BigDecimal(value);
        } else if (classType == LocalDate.class) {
            return LocalDate.parse(value);
        }
        return value;
    }
}
