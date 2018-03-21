package es.maqui.backend.repository;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}