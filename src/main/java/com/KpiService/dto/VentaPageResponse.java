package com.KpiService.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaPageResponse {

    private List<VentaResponse> content;

    private boolean empty;
    private boolean first;
    private boolean last;

    private int number;
    private int numberOfElements;
    private int size;

    private long totalElements;
    private int totalPages;
}