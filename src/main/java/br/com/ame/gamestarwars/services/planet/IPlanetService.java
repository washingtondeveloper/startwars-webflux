package br.com.ame.gamestarwars.services.planet;

import br.com.ame.gamestarwars.document.Planet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPlanetService {
	
	Flux<Planet> getAllPlanets();
	Mono<Planet> getById(String id);
	Mono<Planet> getByName(String name);
	Mono<Planet> save(Planet planet);
	Mono<Void> removeById(String id);
}
