package Utils;

import Entities.User;

public class UserWithImpact {
    private User user;
    private Double impact;

    public UserWithImpact(User user, Double impact) {
        this.user = user;
        this.impact = impact;
    }

    public User getUser() {
        return user;
    }

    public Double getImpact() {
        return impact;
    }
}

