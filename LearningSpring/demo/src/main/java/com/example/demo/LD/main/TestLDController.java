package com.example.demo.LD.main;

import com.launchdarkly.sdk.ContextKind;
import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.LDValue;
import com.launchdarkly.sdk.server.LDClient;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestLDController {

    private final LDClient ldClient;

    private static final String KIND = "kioskMachine";

    private final RestTemplate restTemplate = new RestTemplate();

    private final String GET_MEMBERS = "/api/v2/members";

    private final String LD_URL = "https://app.launchdarkly.com";

    private final String GET_CONTEXT_KINDS = "/api/v2/projects/toggle-runner-demo/context-kinds";

    private final String GET_CONTEXT_KIND =
            "/api/v2/projects/toggle-runner-demo/environments/featuretest/contexts/%s/%s";

    private final String GET_ATTRIBUTE_CK =
            "/api/v2/projects/toggle-runner-demo/environments/featuretest/context-attributes/%s";

    private final String GET_CK_INSTANCE =
            "/api/v2/projects/toggle-runner-demo/environments/featuretest/context-instances/search";

    private final String DELETE_CK_INSTANCE =
    "/api/v2/projects/toggle-runner-demo/environments/featuretest/context-instances/%s";

    private final String READER_TOKEN = "api-4aad7798-7d05-4210-a565-4ab50b148874";

    private final String WRITER_TOKEN = "api-eed2537f-ec48-49af-bacd-d4b1cc79a131";

    //get all members
    @RequestMapping("/members")
    public Object getMembers() {
        HttpEntity httpEntity = new HttpEntity(getHeadersByReaderToken());
        return restTemplate.exchange(LD_URL.concat(GET_MEMBERS), HttpMethod.GET, httpEntity, Object.class).getBody();
    }

    public HttpHeaders getHeadersByReaderToken() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", READER_TOKEN);
        return httpHeaders;
    }

    public HttpHeaders getHeadersByWriterToken() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", WRITER_TOKEN);
        httpHeaders.set("LD-API-Version", "beta");
        httpHeaders.set("content-type", "application/json");
        return httpHeaders;
    }

    //get all cks
    @RequestMapping("/contextKinds")
    public Object getContextKinds() {
        HttpEntity httpEntity = new HttpEntity(getHeadersByWriterToken());
        return restTemplate.exchange(LD_URL.concat(GET_CONTEXT_KINDS), HttpMethod.GET, httpEntity, Object.class).getBody();
    }

    //get ck by K and K
    @RequestMapping("/contextKind/{kind}/{key}")
    public Object getContextKind(@PathVariable String kind, @PathVariable String key) {
        HttpEntity httpEntity = new HttpEntity(getHeadersByWriterToken());
        return restTemplate.exchange(LD_URL.concat(String.format(GET_CONTEXT_KIND, kind, key)), HttpMethod.GET, httpEntity, Object.class).getBody();
    }

    //get attr
    @RequestMapping("/contextKind/attribute/{attributeName}")
    public Object getAttributeOfContextKind(@PathVariable String attributeName) {
        HttpEntity httpEntity = new HttpEntity(getHeadersByWriterToken());
        return restTemplate.exchange(LD_URL.concat(String.format(GET_ATTRIBUTE_CK, attributeName)), HttpMethod.GET, httpEntity, Object.class).getBody();
    }

    @GetMapping
    public Map<String, LDValue> getKiosks() {
        LDContext context = LDContext.create(String.valueOf(ContextKind.of(KIND)));
//        ldClient.identify(context);
        return ldClient.allFlagsState(context).toValuesMap();

    }

    //Create or update Context
    @PostMapping
    public Boolean createOrUpdateContext(@RequestBody CreateContextRequest request) {
        LDContext context = LDContext.builder(request.getName())
                .kind(ContextKind.of(request.getKind()))
                .key(request.getKey())
                .name(request.getName())
                .set("isMaintenanceOfDemo", request.isValue())
                .build();

        return ldClient.boolVariation(request.getKey(), context, true);

    }

    //Remove Context
    @PostMapping("/removeContextInstance")
    public Object removeContextInstance(@RequestBody CreateContextRequest request) {
        HttpEntity httpEntity = new HttpEntity(getHeadersByWriterToken());
//        String filter = "?filter=kindkeys%2520contains%2520%255B%2522"+request.getKind()+
//                "%253A"+request.getKey()+"%2522%255D";
        ContextInstance contextInstance =
                restTemplate.exchange(LD_URL.concat(GET_CK_INSTANCE), HttpMethod.POST, httpEntity, ContextInstance.class).getBody();
        String contextId = contextInstance.getItems().stream().filter(item -> item.getContext()
                .equals(Context.builder().kind(request.getKind()).key(request.getKey()).name(request.getName()).build()))
                .findFirst().orElseThrow(() -> new RuntimeException("Input context invalid")).getId();

        restTemplate.exchange(LD_URL.concat(String.format(DELETE_CK_INSTANCE, contextId)), HttpMethod.DELETE, httpEntity, Object.class).getBody();
        return true;
    }

    @RequestMapping("/getToken")
    public Object getToken() {
        String key = "ApMlgjRaS8SwXBdfIMwWGQ";
        String apiSecret = "fC0YiLTYNmi1rXMuWR1voLJyyOxAWv61Evng";
        JWTClaimsSet.Builder claimsSet = new JWTClaimsSet.Builder().issuer(key)
                .issueTime(new Date(System.currentTimeMillis()))
                .expirationTime(new Date(System.currentTimeMillis() + (1000 * 60)));
        Map<String, Object> payload = new HashMap<>();
        if (!CollectionUtils.isEmpty(payload)) {
            payload.entrySet().stream().forEach(entry -> claimsSet.claim(entry.getKey(), entry.getValue()));
        }
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        SignedJWT signedJWT = new SignedJWT(jwsHeader, claimsSet.build());
        try {
            JWSSigner signer = new MACSigner(apiSecret);
            signedJWT.sign(signer);
            return signedJWT.serialize();
        }
        catch (JOSEException e) {
            throw new RuntimeException("Error when generate JWT Token: " + e.getMessage());
        }
    }
}
