package com.earth.withdang.domain;

//address varchar(255) primary key,
//danggeun_id Integer not null,
//sequence int not null,

public class DanggeunPhotoDTO {

	private String address;
	private Integer danggeun_id;
	private int sequence;
	
	public DanggeunPhotoDTO() {}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDanggeun_id() {
		return danggeun_id;
	}

	public void setDanggeun_id(Integer danggeun_id) {
		this.danggeun_id = danggeun_id;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	};
	
}
