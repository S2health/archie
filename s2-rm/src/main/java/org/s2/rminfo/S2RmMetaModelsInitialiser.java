package org.s2.rminfo;

import com.nedap.archie.archetypevalidator.PrimitiveObjectConstraintHelper;
import com.nedap.archie.rminfo.MetaModelsInitialiser;
import com.nedap.archie.rminfo.RMObjectMapperProvider;
import com.nedap.archie.rminfo.ReferenceModels;
import com.nedap.archie.tools.rmobjectvalidator.S2PrimitiveObjectConstraintHelper;
import org.s2.serialisation.json.S2RmObjectMapperProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Global meta-model access for openEHR RM
 */
public class S2RmMetaModelsInitialiser extends MetaModelsInitialiser {

    private static final Logger logger = LoggerFactory.getLogger(S2RmMetaModelsInitialiser.class);

    /**
     * Returns the model info lookups that are built in archie and are available in the classloader. Add the reference
     * models to your dependencies to make this return values
     * @return
     */
    public ReferenceModels getNativeRms() {
        ReferenceModels result = new ReferenceModels();

        RMObjectMapperProvider provider = null;
        try {
            Class<?> objectMapperProvider = S2RmObjectMapperProvider.class;
            Constructor<?> getProviderInstance = objectMapperProvider.getConstructor();
            provider = (RMObjectMapperProvider) getProviderInstance.newInstance();
        }
        catch (InstantiationException | NoSuchMethodException |  IllegalAccessException | InvocationTargetException ignored) {
        }

        result.registerModel(S2RmInfoLookup.getInstance(), provider);

        return result;
    }

    @Override
    public HashMap<String, PrimitiveObjectConstraintHelper> getPrimitiveObjectConstraintHelpers() {
        HashMap<String, PrimitiveObjectConstraintHelper> result = new HashMap<>();
        result.put("s2", new S2PrimitiveObjectConstraintHelper());
        return result;
    }

    public String getParentPackage() {
        return "org.s2";
    }

}