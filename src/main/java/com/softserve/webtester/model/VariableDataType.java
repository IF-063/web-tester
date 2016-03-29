package com.softserve.webtester.model;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Enumeration of the variable data types can be used in the {@link Variable} instance.
 * 
 * @author Taras Oglabyak
 */
public enum VariableDataType {

    LONG {
        @Override
        public Stream<?> getRandomStream(int length) {
            return new Random().ints(48, 58)
                    .mapToObj(i -> (char) i)
                    .limit(length);
        }
    },
    
    DOUBLE {
        @Override
        public Stream<?> getRandomStream(int length) {
            return new Random().ints(48, 58)
                    .mapToObj(i -> (char) i)
                    .limit(length + 2);
        }
    },
    
    STRING {
        @Override
        public Stream<?> getRandomStream(int length) {
            return new Random().ints(48, 122)
                        .filter(i -> (i < 58 || i > 65) && (i < 90 || i > 97))
                        .mapToObj(i -> (char) i)
                        .limit(length);
        }
    };
    
    public abstract Stream<?> getRandomStream(int length);

}