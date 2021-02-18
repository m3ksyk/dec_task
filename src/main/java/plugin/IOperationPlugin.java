package plugin;

import java.util.List;

public interface IOperationPlugin {

    List<Object> fetchInputData();

    Object performOperation(List<Object> inputs);
}
