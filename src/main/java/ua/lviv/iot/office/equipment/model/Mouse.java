package ua.lviv.iot.office.equipment.model;

import javax.persistence.Entity;

@Entity
public class Mouse extends AbstractOfficeEquipment {
  private int numberOfButton;

  @Override
  public String getHeaders() {
    return super.getHeaders() + "," + "numberOfButton ";
  }

  @Override
  public String toCSV() {
    return super.toCSV() + "," + numberOfButton;
  }

  public int getNumberOfButton() {
    return numberOfButton;
  }

  public void setNumberOfButton(int numberOfButton) {
    this.numberOfButton = numberOfButton;
  }

  public Mouse(int productionYear, String producerName, double priceInUaH, String color,
               double weightInKilograms, CableForPower cableForPower, int numberOfButton) {
    super(productionYear, producerName, priceInUaH, color, weightInKilograms, cableForPower);
    this.numberOfButton = numberOfButton;
  }

  public Mouse() {

  }
}
