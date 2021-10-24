package com.msa.product;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Review {
    private Long id;
    private String content;
    private Long productId;
}
