package com.checkr.interviews;

public enum OptionKey {
    company_name {
        public String getOptionkey() {
            return "company_name";
        }
        public int getArrayPosition() {
            return 1;
        }
    },
    city {
        public String getOptionkey() {
            return "city";
        }
        public int getArrayPosition() {
            return 4;
        }
    },
    state {
        public String getOptionkey() {
            return "state";
        }
        public int getArrayPosition() {
            return 5;
        }
    },
    round {
        public String getOptionkey() {
            return "round";
        }
        public int getArrayPosition() {
            return 9;
        }
    };
    

    public abstract String getOptionkey();
    public abstract int getArrayPosition();
}