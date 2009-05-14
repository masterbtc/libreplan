package org.navalplanner.business.resources.entities;

import org.navalplanner.business.common.exceptions.InstanceNotFoundException;
import org.navalplanner.business.resources.daos.ResourcesDaoRegistry;

// FIXME: Alternatively, Resource can be modeled with the style:
// Resource.getParent() & Resource.getChilds(). This way, Resource does not
// depend on ResourceGroup. However, such an option allows combinations not
// semantically correct (e.g. a simple resource, such as Worker, could be the 
// child another simple resource, general methods like getChilds() do not make
// sense for simple entities, etc.). In consequence, I prefer the modeling 
// option shown below.

/**
 * This class acts as the base class for all resources.
 * @author Fernando Bellas Permuy <fbellas@udc.es>
 */
public abstract class Resource {

    private Long id;

    @SuppressWarnings("unused")
    private long version;

    public Long getId() {
        return id;
    }

    public abstract int getDailyCapacity();

    /**
     * It removes the resource from the database and updates references. The
     * default implementation removes the resource from the resource group it
     * belongs to (if it belongs to someone) and from the database. This
     * implementation should be valid for simple resources.
     */
    public void remove() {
        /* Remove from the database. */
        try {
            ResourcesDaoRegistry.getResourceDao().remove(getId());
        } catch (InstanceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
