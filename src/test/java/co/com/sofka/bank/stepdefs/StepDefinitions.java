package co.com.sofka.bank.stepdefs;

import co.com.sofka.bank.domain.clientinfo.Client;
import co.com.sofka.bank.infrastructure.entrypoints.ClientController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class StepDefinitions {

    @Autowired
    private ClientController clientController;

    private String dni;
    private Client client;

    @Given("a client exists with DNI {string}")
    public void a_client_exists_with_DNI(String dni) {
        this.dni = dni;
    }

    @When("the client information is requested for DNI {string}")
    public void the_client_information_is_requested_for_DNI(String dni) {
        Client response = clientController.findClient(dni);
        if (!Objects.isNull(response)) {
            this.client = response;
        } else {
            this.client = null;
        }
    }

    @Then("the client information should be returned")
    public void the_client_information_should_be_returned() {
        assertEquals(dni, client.dni());
    }

    @Given("no client exists with DNI {string}")
    public void no_client_exists_with_DNI(String dni) {
        this.dni = dni;
    }

    @Then("a client not found error should be returned")
    public void a_client_not_found_error_should_be_returned() {
        assertNull(client);
    }
}
