package com.example.demo.LD.main;

import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.server.Components;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;

public class HelloLD {

    // Set SDK_KEY to your LaunchDarkly SDK key.
    static final String SDK_KEY = "sdk-47d493c8-9c88-4077-b2d6-7830e1a7004f";

    // Set FEATURE_FLAG_KEY to the feature flag key you want to evaluate.
    static final String FEATURE_FLAG_KEY = "haitest-boolean-flag";

    private static void showMessage(String s) {
        System.out.println("*** " + s);
        System.out.println();
    }

    public static void main(String... args) throws Exception {
        if (SDK_KEY.equals("")) {
            showMessage("Please edit Hello.java to set SDK_KEY to your LaunchDarkly SDK key first");
            System.exit(1);
        }

        LDConfig config = new LDConfig.Builder()
                .events(Components.noEvents())
                .build();

        LDClient client = new LDClient(SDK_KEY);

        if (client.isInitialized()) {
            showMessage("SDK successfully initialized!");
        } else {
            showMessage("SDK failed to initialize");
            System.exit(1);
        }

        // Set up the evaluation context. This context should appear on your LaunchDarkly contexts
        // dashboard soon after you run the demo.
//    LDContext context = LDContext.builder("example-user-key")
//                            .name("Sandy")
//                            .build();

        LDUser user = new LDUser.Builder("Xavier Poon")
                .build();

        boolean flagValue = client.boolVariation(FEATURE_FLAG_KEY, user, false);

        showMessage("Feature flag '" + FEATURE_FLAG_KEY + "' is " + flagValue + " for this context");

        // Here we ensure that the SDK shuts down cleanly and has a chance to deliver analytics
        // events to LaunchDarkly before the program exits. If analytics events are not delivered,
        // the context attributes and flag usage statistics will not appear on your dashboard. In
        // a normal long-running application, the SDK would continue running and events would be
        // delivered automatically in the background.
        client.close();
    }
}
