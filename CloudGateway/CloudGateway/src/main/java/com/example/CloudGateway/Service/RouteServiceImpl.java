package com.example.CloudGateway.Service;

import com.example.CloudGateway.Model.ApiRoute;
import com.example.CloudGateway.Repository.RouteRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RouteServiceImpl implements RouteService{

	@Autowired
	private RouteRepositroy routeRepository;
	
	 public RouteServiceImpl(RouteRepositroy routeRepository) {
	        this.routeRepository = routeRepository;
	    }

	    @Override
	    public Flux<ApiRoute> getAll() {
	        return this.routeRepository.findAll();
	    }

	    @Override
	    public Mono<ApiRoute> create(ApiRoute apiRoute) {
	        Mono<ApiRoute> route = this.routeRepository.save(apiRoute);
	        return route;
	    }

	    @Override
	    public Mono<ApiRoute> getById(String id) {
	        return this.routeRepository.findById(id);
	    }
}