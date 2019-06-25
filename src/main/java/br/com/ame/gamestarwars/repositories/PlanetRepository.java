package br.com.ame.gamestarwars.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.ame.gamestarwars.document.Planet;
import reactor.core.publisher.Mono;

public interface PlanetRepository extends ReactiveMongoRepository<Planet, String> {
	
	Mono<Planet> findByName(String name);

}
