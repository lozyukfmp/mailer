package by.samsolutions.imgcloud.nodeentity;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseEntity implements Serializable {
    protected Long uuid;
}
