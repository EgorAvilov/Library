package by.itechart.library.controller.util.impl;

import by.itechart.library.controller.util.api.Pagination;
import lombok.Getter;

public class PaginationImpl implements Pagination {

    @Getter
    private int currentPage;

    @Getter
    private int recordsPerPage;

    @Getter
    private int numberOfRows;


}
