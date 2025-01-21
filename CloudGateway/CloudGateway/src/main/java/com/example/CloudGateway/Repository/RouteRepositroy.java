package com.example.CloudGateway.Repository;

import com.example.CloudGateway.Model.ApiRoute;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepositroy extends ReactiveCrudRepository<ApiRoute, String> {

}
