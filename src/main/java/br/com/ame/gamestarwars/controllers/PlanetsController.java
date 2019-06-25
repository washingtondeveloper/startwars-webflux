package br.com.ame.gamestarwars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ame.gamestarwars.document.Planet;
import br.com.ame.gamestarwars.services.planet.IPlanetService;
import br.com.ame.gamestarwars.services.starwars.IStarWarsService;
import br.com.ame.gamestarwars.vo.StarWarsVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/planets")
public class PlanetsController {

	@Autowired
	@Qualifier("planet")
	private IPlanetService service;

	@Autowired
	@Qualifier("starwars")
	private IStarWarsService starwars;

	@GetMapping
	public Flux<Planet> getAllPlanets() {
		return service.getAllPlanets();
	}

	@GetMapping("/api")
	public Flux<StarWarsVO> getAPI() {
		return starwars.getPlanets();
	}

	@GetMapping("/{id}")
	public Mono<Planet> getById(@PathVariable String id) {
		return service.getById(id);
	}

	@GetMapping("/{name}/name")
	public Mono<Planet> getByName(@PathVariable String name) {
		return service.getByName(name);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Planet> savePlanet(@RequestBody Planet planet) {
		return service.save(planet);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Mono<Void> removePlanet(@PathVariable String id) {
		return service.removeById(id);
	}
}
