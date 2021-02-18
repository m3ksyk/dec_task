package factory;

import lombok.extern.log4j.Log4j2;
import model.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import plugin.AdditionOperationPlugin;
import plugin.IOperationPlugin;
import rest.RestController;
import util.InputDataUtil;

@Log4j2
@Service
@Configurable
public class OperationPluginFactory {

    @Autowired
    RestController restController;

    @Autowired
    InputDataUtil inputDataUtil;

    public IOperationPlugin getPlugin(OperationType operationType) {
        log.info("Fetching plugin");
        switch (operationType){
            case ADDITION : return new AdditionOperationPlugin(restController, inputDataUtil);
            default : return null;
        }
    }
}
