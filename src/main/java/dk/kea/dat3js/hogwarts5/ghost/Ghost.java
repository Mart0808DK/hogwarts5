package dk.kea.dat3js.hogwarts5.ghost;

import dk.kea.dat3js.hogwarts5.house.House;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Ghost {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String realName;
    @ManyToOne
    private House house;

    public Ghost() {
    }

    public Ghost(Integer id, String name, String realName, House house) {
        this.id = id;
        this.name = name;
        this.realName = realName;
        this.house = house;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
