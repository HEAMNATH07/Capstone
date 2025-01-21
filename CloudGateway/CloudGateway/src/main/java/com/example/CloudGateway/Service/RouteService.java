package com.example.CloudGateway.Service;

import com.example.CloudGateway.Model.ApiRoute;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteService {
	public Flux<ApiRoute> getAll();
	public Mono<ApiRoute> create(ApiRoute apiRoute);
	public Mono<ApiRoute> getById(String id);
}