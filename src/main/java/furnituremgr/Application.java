package furnituremgr;


import furnituremgr.model.Furniture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class Application {

    public static final SessionFactory sessionFactory = buildSessionFactory();


    public static void main(String[] args) {

        Furniture furniture = new Furniture.FurnitureBuilder("chair","ikea")
                .withLink("http://somelnk.com")
                .withPrice("50")
                .build();
        int id = save(furniture);

        Furniture f = findById(id);
        f.setPrice("123");
        update(f);
        System.out.println("UPDATED!");

        delete(f);
        System.out.println("DELETED!");


        for (Furniture ff : fetchFurniture()){
            System.out.println(ff.toString());
        }



    }

    private static int save(Furniture furniture){
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        int id = (int) session.save(furniture);
        session.getTransaction().commit();

        session.close();
        return id;
    }

    private static List<Furniture> fetchFurniture(){
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Furniture> criteria = builder.createQuery(Furniture.class);
        criteria.from(Furniture.class);

        List<Furniture> furniture = session.createQuery(criteria).getResultList();
        session.close();

        return furniture;

    }

    private static Furniture findById(int id){
        Session session = sessionFactory.openSession();

        Furniture furniture = session.get(Furniture.class,id);


        session.close();

        return furniture;

    }

    private static void update(Furniture furniture) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(furniture);


        session.getTransaction().commit();

        session.close();
    }

    private static void delete(Furniture furniture){
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.delete(furniture);

        session.getTransaction().commit();

        session.close();
    }


    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
       return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
}
