package br.com.ame.gamestarwars.controllers;

import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import br.com.ame.gamestarwars.document.Planet;
import br.com.ame.gamestarwars.services.planet.PlanetService;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = PlanetsController.class)
public class PlanetsControllerTest {
	@Autowired
	private WebTestClient client;
	
	@MockBean
	private PlanetService service;
	
	@Test
	public void testGetPlanetShouldBeOk() {
		
		client.get()
			.uri("/planets")
			.exchange()
			.expectStatus()
			.isOk();
	}
	
	@Test
	public void testGetPlanetByIdBeOk() {
		Planet planet = new Planet();
		planet.setId("333");
		planet.setName("namePlanet");
		
		given(service.getById("333")).willReturn(Mono.just(planet));
		
		client.get()
			.uri("/planets/{id}", "333").accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody(Planet.class)
			.isEqualTo(planet);
	}
	
	@Test
	public void testGetPlanetByNameBeOk() {
		
		Planet planet = new Planet();
		planet.setId("333");
		planet.setName("namePlanet");
		
		given(service.getByName("namePlanet")).willReturn(Mono.just(planet));
		
		client.get()
			.uri("/planets/{name}/name", "namePlanet").accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody(Planet.class)
			.isEqualTo(planet);
	}
	
	@Test
	public void testDeletePlanetById() {
		
		given(service.removeById("111")).willReturn(Mono.empty());
		
		client.delete().uri("/planets/{id}", "111")
			.exchange()
				.expectStatus().isAccepted();
	}
	
	@Test
	public void testPostSavePlanet() {
		Planet planet = new Planet();
		planet.setId("222");
		planet.setName("newPlanet");
		
		given(service.save(planet)).willReturn(Mono.just(planet));
		
		client.post().uri("/planets")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(planet))
			.exchange()
				.expectBody(Planet.class);
	}
}
