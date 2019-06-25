package br.com.ame.gamestarwars.services.planet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.ame.gamestarwars.document.Planet;
import br.com.ame.gamestarwars.repositories.PlanetRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Qualifier("planet")
public class PlanetService implements IPlanetService {
	
	@Autowired
	private PlanetRepository repository;

	@Override
	public Flux<Planet> getAllPlanets() {
		return repository.findAll();
	}

	@Override
	public Mono<Planet> getById(String id) {
		return repository.findById(id);
	}

	@Override
	public Mono<Planet> getByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Mono<Void> removeById(String id) {
		return repository.deleteById(id);
	}

	@Override
	public Mono<Planet> save(Planet planet) {
		return repository.save(planet);
	}

}
