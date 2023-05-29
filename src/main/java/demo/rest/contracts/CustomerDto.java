package demo.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Long id;

    private String name;

    private String phone;

    private Integer async;
}
