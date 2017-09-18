package com.jane.spring.study.helloworld.pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * PaginationResult class.
 */
public class PaginationResult<T> {

    private final List<T> entities;
    private final long currentPage;
    private final long totalPages;
    private final List<Long> pages;

    public PaginationResult(List<T> entities, long currentPage, long totalPages) {
        this.entities = entities;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.pages = buildPages();
    }

    public List<T> getEntities() {
        return entities;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public List<Long> getPages() {
        return pages;
    }

    private List<Long> buildPages() {
        List<Long> pages = new ArrayList<>(10);

        if (totalPages <= 3L) {
            for (long i = 1L; i <= pages.size(); i++) {
                pages.add(i);
            }
            return pages;
        }

        if (totalPages > 3L) {
            if (currentPage == 1L) {
                pages.add(currentPage);
                pages.add(currentPage + 1L);
                pages.add(currentPage + 2L);
                pages.add(0L);
                pages.add(totalPages);
            } else if (currentPage == 2L) {
                pages.add(1L);
                pages.add(currentPage);
                pages.add(currentPage + 1L);
                pages.add(currentPage + 2L);
                pages.add(0L);
                pages.add(totalPages);
            } else if (currentPage == 3L) {
                pages.add(currentPage - 2L);
                pages.add(currentPage - 1L);
                pages.add(currentPage);
                pages.add(currentPage + 1L);
                pages.add(currentPage + 2L);
                pages.add(0L);
                pages.add(totalPages);
            } else if (currentPage == 4L) {
                pages.add(1L);
                pages.add(currentPage - 2L);
                pages.add(currentPage - 1L);
                pages.add(currentPage);
                pages.add(currentPage + 1L);
                pages.add(currentPage + 2L);
                pages.add(0L);
                pages.add(totalPages);
            } else if (currentPage == totalPages - 3L) {
                pages.add(1L);
                pages.add(0L);
                pages.add(currentPage - 2L);
                pages.add(currentPage - 1L);
                pages.add(currentPage);
                pages.add(currentPage + 1L);
                pages.add(currentPage + 2L);
                pages.add(totalPages);
            } else if (currentPage == totalPages - 2L) {
                pages.add(1L);
                pages.add(0L);
                pages.add(currentPage - 2L);
                pages.add(currentPage - 1L);
                pages.add(currentPage);
                pages.add(currentPage + 1L);
                pages.add(totalPages);
            } else if (currentPage == totalPages - 1L) {
                pages.add(1L);
                pages.add(0L);
                pages.add(currentPage - 2L);
                pages.add(currentPage - 1L);
                pages.add(currentPage);
                pages.add(totalPages);
            } else if (currentPage == totalPages) {
                pages.add(1L);
                pages.add(0L);
                pages.add(currentPage - 2L);
                pages.add(currentPage - 1L);
                pages.add(currentPage);

            } else {
                pages.add(1L);
                pages.add(0L);
                pages.add(currentPage - 2L);
                pages.add(currentPage - 1L);
                pages.add(currentPage);
                pages.add(currentPage + 1L);
                pages.add(currentPage + 2L);
                pages.add(0L);
                pages.add(totalPages);
            }
        }
        return pages;
    }
}
