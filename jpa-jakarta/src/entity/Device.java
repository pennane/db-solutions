
package entity;

import jakarta.persistence.*;

@Entity
@Table(name="DEVICE")
public class Device {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="deviceId", nullable=false)
    private int deviceId;

	@Column(name="description")
    private String description;

    public Device(String description) {
        this.description = description;
    }

    public Device() {
    }



	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getDescription() { return description;}

	public void setDescription(String description) {this.description = description;}

	@Override
	public String toString() {
		return "Device [deviceId=" + deviceId + ", description=" + description + "]";
	}
}

