package model;

import lombok.Data;

@Data
public class CSRNGResponse {
    String status;
    int min;
    int max;
    int random;
}
