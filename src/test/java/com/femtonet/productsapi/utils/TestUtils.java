package com.femtonet.productsapi.utils;

import com.femtonet.productsapi.payload.ProductResponse;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class TestUtils {

    public static Matcher<? super List<ProductResponse>> isInAscendingOrder() {
        return new TypeSafeMatcher<List<ProductResponse>>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("The product list is not sorted!");
            }

            @Override
            protected boolean matchesSafely(List<ProductResponse> item) {
                for (int i = 0; i < item.size() - 1; i++) {
                    if (item.get(i).getSortPoint() >= item.get(i + 1).getSortPoint()) return false;
                }
                return true;
            }
        };
    }
}
