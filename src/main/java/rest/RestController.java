package rest;

import com.google.gson.Gson;
import factory.OperationPluginFactory;
import lombok.extern.log4j.Log4j2;
import model.CSRNGResponse;
import model.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import plugin.IOperationPlugin;
import util.RestUtil;

import java.util.Map;


@Controller
@org.springframework.web.bind.annotation.RestController
@Log4j2
public class RestController {

    private static final String RANDOM_URI = "https://www.random.org/integers/?num=1&min={min}&max={max}&col=1&base=10&format=plain&rnd=new";
    private static final String CSRNG_URI = "https://csrng.net/csrng/csrng.php?min={min}&max={max}";

    @Autowired
    RestUtil restUtil;

    @Autowired
    OperationPluginFactory pluginFactory;

    public Integer getNumberFromRandom(Map variables){
        log.info("Fetching data from random.org");
        RestTemplate restTemplate = restUtil.getRestTemplate();
        String result = restTemplate.getForObject(RANDOM_URI, String.class, variables);
        result = result.trim();

        return Integer.valueOf(result);
    }

    public Integer getNumberFromCSRNG(Map variables) {
        log.info("Fetching data from csrng.net");

        RestTemplate restTemplate = restUtil.getRestTemplate();
        String response = restTemplate.getForObject(CSRNG_URI, String.class, variables);

        Gson gson = new Gson();
        CSRNGResponse[] results = gson.fromJson(response, CSRNGResponse[].class);
        CSRNGResponse result = results[0];

        return result.getRandom();
    }

    @RequestMapping(value="/test", method= RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody String getTest(@RequestParam("op") String op) {
        log.info("Testing");

        IOperationPlugin operationPlugin = pluginFactory.getPlugin(OperationType.valueOf(op.toUpperCase()));
        Object result = operationPlugin.performOperation(operationPlugin.fetchInputData());
        return result.toString();
    }

}
