package com.redhat.todo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiParam;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.redhat.todo.model.Todo;

@Controller
@RequestMapping("${openapi.todo.base-path:}")
public class TodosApiController implements TodosApi {

    @Autowired
    RestTemplate rt;

    @Value("${backendUrl}")
    String backendUrl;

    private final NativeWebRequest request;


    @org.springframework.beans.factory.annotation.Autowired
    public TodosApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(value = "completed", required = false) @Valid Boolean completed) {
        return rt.exchange(backendUrl + "/todos", HttpMethod.GET, null, new ParameterizedTypeReference<List<Todo>>(){} );
    }
}
