package org.example.hm3.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProductDTO {
    private Long id;
    private String name;
    private float price;
    private int productCount;
}
