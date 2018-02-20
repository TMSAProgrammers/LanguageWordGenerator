package com.company;

abstract class WeightedLists {
    Object[] items;

    //Array getters
    public Object[] getItems() {
        return items;
    }

    //Constructor
    WeightedLists(Object[] itemIn) {
        //Set items to the in item array (initialize items)
        items = itemIn;
    }

    //Payload method
    abstract Object chooseRandomItem();
}
