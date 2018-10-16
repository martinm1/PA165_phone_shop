package cz.muni.fi.pa165.smartphonEShop.entity;

import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by Stefan Holecko
 * Class represents:
 */

public class Phone {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable=false)
    private String modelName;
    @Column(nullable=false)
    private int price;
    @Column(nullable=false)
    private String technicalInfo;
    @Column(nullable=false)
    private Manufacturer manufacturer;
    @Column(nullable=false)
    private Long stockId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTechnicalInfo() {
        return technicalInfo;
    }

    public void setTechnicalInfo(String technicalInfo) {
        this.technicalInfo = technicalInfo;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return Objects.equals(getModelName(), phone.getModelName()) &&
                Objects.equals(getTechnicalInfo(), phone.getTechnicalInfo()) &&
                getManufacturer() == phone.getManufacturer();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModelName(), getTechnicalInfo(), getManufacturer());
    }
}
