package com.example.demo.LD.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiDocs")
public class ApiLDController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String GET_MEMBERS = "/api/v2/members";

    private final String LD_URL = "https://app.launchdarkly.com";

    private final String GET_CONTEXT_KINDS = "/api/v2/projects/toggle-runner-demo/context-kinds";

    private final String GET_CONTEXT_KIND =
            "/api/v2/projects/toggle-runner-demo/environments/featuretest/contexts/%s/%s";


    private final String READER_TOKEN = "api-4aad7798-7d05-4210-a565-4ab50b148874";

    private final String WRITER_TOKEN = "api-eed2537f-ec48-49af-bacd-d4b1cc79a131";

    @RequestMapping("/members")
    public Object getMembers() {
        HttpEntity httpEntity = new HttpEntity(getHeaders());
        return restTemplate.exchange(LD_URL.concat(GET_MEMBERS), HttpMethod.GET, httpEntity, Object.class).getBody();
    }

    public HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", READER_TOKEN);
        httpHeaders.set("LD-API-Version", "beta");
        return httpHeaders;
    }

    @RequestMapping("/contextKinds")
    public Object getContextKinds() {
        HttpEntity httpEntity = new HttpEntity(getHeaders());
        return restTemplate.exchange(LD_URL.concat(GET_CONTEXT_KINDS), HttpMethod.GET, httpEntity, Object.class).getBody();
    }

    @RequestMapping("/contextKind/{kind}/{key}")
    public Object getContextKind(@PathVariable String kind, @PathVariable String key) {
        HttpEntity httpEntity = new HttpEntity(getHeaders());
        return restTemplate.exchange(LD_URL.concat(String.format(GET_CONTEXT_KIND, kind, key)), HttpMethod.GET, httpEntity, Object.class).getBody();
    }
}
