package sample;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyEntityManagerFactory {
    final private EntityManagerFactory managerFactory;
    private static MyEntityManagerFactory instance = null;

    private MyEntityManagerFactory() {
        managerFactory = Persistence.createEntityManagerFactory("Week_9");
    }

    public static MyEntityManagerFactory getInstance() {
        if (instance == null) {
            instance = new MyEntityManagerFactory();
        } else if (!instance.managerFactory.isOpen()) {
            instance = new MyEntityManagerFactory();
        }
        return instance;
    }

    public EntityManagerFactory getFactory() {
        return managerFactory;
    }

    public void closeFactory() {
        managerFactory.close();
    }
}
