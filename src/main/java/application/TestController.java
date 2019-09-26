package application;


import model.CalculatedPrice;
import model.EventProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    private CalculatePriceService calculatePriceService;

    @RequestMapping("/home")
    public String home() {
        return "App is working!";
    }

    @PostMapping(value = "/calculatePrice", consumes = "application/json", produces = "application/json")
    public CalculatedPrice calculatedPrice(@RequestBody EventProductRequest eventProductRequest) {
        return calculatePriceService.getPriceDetails(eventProductRequest);
    }

}