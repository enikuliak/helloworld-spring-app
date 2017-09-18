package com.jane.spring.study.helloworld.pagination;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Pagination result test.
 */
public class PaginationResultTest {

    @Test
    public void testMiddleRange() {
        PaginationResult<Object> paginationResult =
                new PaginationResult<>(Collections.emptyList(), 50, 100);

        assertEquals(Arrays.asList(1L, 0L, 48L, 49L, 50L, 51L, 52L, 0L, 100L),
                paginationResult.getPages());
    }

    @Test
    public void testFistPage(){
        PaginationResult<Object> paginationResult =
                new PaginationResult<>(Collections.emptyList(), 1, 100);

        assertEquals(Arrays.asList(1L, 2L, 3L, 0L, 100L), paginationResult.getPages());
    }

    @Test
    public void testSecondPage(){
        PaginationResult<Object> paginationResult =
                new PaginationResult<>(Collections.emptyList(), 2, 100);

        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 0L, 100L), paginationResult.getPages());
    }

    @Test
    public void testThirdPage(){
        PaginationResult<Object> paginationResult =
                new PaginationResult<>(Collections.emptyList(), 3, 100);

        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L, 0L, 100L), paginationResult.getPages());
    }

    @Test
    public void testFourtPage(){
        PaginationResult<Object> paginationResult =
                new PaginationResult<>(Collections.emptyList(), 4, 100);

        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 0L, 100L), paginationResult.getPages());
    }

    @Test
    public void testLastPage(){
        PaginationResult<Object> paginationResult =
                new PaginationResult<>(Collections.emptyList(), 100, 100);

        assertEquals(Arrays.asList(1L, 0L, 98L, 99L, 100L), paginationResult.getPages());
    }

    @Test
    public void testSEveralLastPages(){
        PaginationResult<Object> paginationResult =
                new PaginationResult<>(Collections.emptyList(), 99, 100);

        assertEquals(Arrays.asList(1L, 0L, 97L, 98L, 99L, 100L), paginationResult.getPages());
    }
}
