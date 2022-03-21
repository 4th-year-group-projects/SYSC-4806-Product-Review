package application;

import application.models.User;

public class JaccardUserHelper implements Comparable<JaccardUserHelper>{
    private User user;
    private Double jaccardDistance;

    public JaccardUserHelper(User user, Double jaccardDistance) {
        this.user = user;
        this.jaccardDistance = jaccardDistance;
    }

    public Double getJaccardDistance() {
        return jaccardDistance;
    }

    public User getUser() {
        return  this.user;
    }

    @Override
    public int compareTo(JaccardUserHelper j) {
        return this.jaccardDistance.compareTo(j.getJaccardDistance());
    }
}
