package plugin;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import rest.RestController;
import util.InputDataUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Component
@AllArgsConstructor
public class AdditionOperationPlugin implements IOperationPlugin {

    RestController restController;
    InputDataUtil inputDataUtil;

    public List<Object> fetchInputData() {
        log.info("Fetching input data");
        Map variables = new HashMap();
        variables.put("min", 0);
        variables.put("max", 100);
        List<Object> input = new ArrayList<>();
        input.add(restController.getNumberFromCSRNG(variables));
        input.add(restController.getNumberFromRandom(variables));
        input.add(inputDataUtil.generateRandomNumber((Integer) variables.get("max")));

        return input;
    }

    public Object performOperation(List<Object> inputs) {
        log.info("Adding input elements");
        Integer result = new Integer(0);
        for(Object o : inputs) {
            try {
                result = Math.addExact(result, (Integer) o);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        log.info("Completed addition. The result is : " + result);

        return result;
    }
}
