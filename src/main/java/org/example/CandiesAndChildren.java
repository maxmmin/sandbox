package org.example;

import java.util.List;

public class CandiesAndChildren {
    public static void main (String[]args) {
        Candy[]candies = new Candy[] {
                new Candy("lolipop"), new Candy("chocolate"), new Candy("lipstick"), new Candy("gum"), new Candy("custom candy")
        };

        Kid[]children = new Kid[] {
                new Kid("John"), new Kid("Lennon"), new Kid("Emma"), new Kid("Jameson")
        };

        List<Candy>candyList = List.of(candies);

        for (Kid kid: children) {
            (new Thread(()-> kid.eat(candyList))).start();
        }
    }
}

class Kid {

    private final String name;

    public Kid (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void eat (Candy candy) {
        if (candy==null) {
            throw new NullPointerException();
        }

        synchronized (Candy.class) {
            if (!candy.isUnpacked()) {
                System.out.printf("Kid %s has eaten %s\n", getName(), candy.getName());
                candy.unpack();
            } else {
                System.out.printf("Kid %s had nothing to eat: somebody already had eaten %s\n", getName(), candy.getName());
            }
        }
    }

    public void eat (List<Candy>candies) {
        candies.forEach(this::eat);
    }
}

class Candy {
    private final String name;

    volatile private boolean isUnpacked = false;

    public Candy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isUnpacked() {
        return isUnpacked;
    }

    public synchronized void unpack () {
        if (isUnpacked()) {
            throw new RuntimeException("Candy is already unpacked");
        } else {
            isUnpacked = true;
        }
    }

}

