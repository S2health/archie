package org.s2.rminfo;

import com.nedap.archie.aom.*;
import com.nedap.archie.aom.primitives.*;
import com.nedap.archie.base.Interval;
import com.nedap.archie.base.RMObject;
import com.nedap.archie.rminfo.RMAttributeInfo;
import com.nedap.archie.rminfo.RMPackageId;
import com.nedap.archie.rminfo.ReflectionModelInfoLookup;

import org.s2.rm.admin.BillingEncounter;
import org.s2.rm.admin.EncounterState;
import org.s2.rm.admin.ServiceEvent;
import org.s2.rm.admin.ServiceEventState;
import org.s2.rm.base.change_control.*;
import org.s2.rm.base.data_types.*;
import org.s2.rm.base.data_types.encapsulated.*;
import org.s2.rm.base.data_types.quantity.*;
import org.s2.rm.base.data_types.text.*;
import org.s2.rm.base.data_types.timing.*;
import org.s2.rm.base.data_types.uri.*;
import org.s2.rm.base.foundation_types.interval.Cardinality;
import org.s2.rm.base.foundation_types.interval.MultiplicityInterval;
import org.s2.rm.base.foundation_types.primitive_types.Uri;
import org.s2.rm.base.foundation_types.terminology.*;
import org.s2.rm.base.foundation_types.time.*;
import org.s2.rm.base.patterns.archetyped.*;
import org.s2.rm.base.model_support.definitions.*;
import org.s2.rm.base.model_support.identification.*;
import org.s2.rm.base.patterns.data_structures.*;
import org.s2.rm.base.patterns.domain_data_structures.*;
import org.s2.rm.care.composition.*;
import org.s2.rm.care.ehr.*;
import org.s2.rm.care.entry.*;
import org.s2.rm.entity.entity.Entity;
import org.s2.rm.entity.entity.EntityRelationship;
import org.s2.rm.entity.entity.EntityRelationshipGroup;
import org.s2.rm.entity.entity_kind.EntityKind;
import org.s2.rm.entity.entity_kind.EntityKindRelationship;
import org.s2.rm.entity.entity_kind.EntityKindRelationshipGroup;
import org.s2.rm.entity.entity_kind.continuant.*;
import org.s2.rm.entity.entity_kind.occurrent.OccurrentKind;
import org.s2.rm.entity.entity_kind.occurrent.ProcessKind;
import org.s2.rm.entity.occurrent.CareProcess;
import org.s2.rm.entity.social_entity.*;
import org.s2.rm.entity.physical_entity.*;
import org.s2.rm.entity.resource.*;
import org.s2.util.enumerations.EnumerationVar;
import org.s2.util.enumerations.EnumerationVarInteger;
import org.s2.util.enumerations.EnumerationVarString;

import java.math.BigDecimal;
import java.util.*;

public class S2RmInfoLookup extends ReflectionModelInfoLookup {

    public static final String RM_VERSION = "0.8.0";

    private static S2RmInfoLookup instance;

    private S2RmInfoLookup() {
        super(new S2ModelNamingStrategy(), RMObject.class);
    }

    /**
     * This list of classes generated in this method corresponds to any class that may be mentioned
     * in an archetype of this RM. Its extension for openEHR RM covers the following classes:
     * - classes in the supplier closures (classes reachable from via RM supplier relationships)
     *   of all classes in the top-level model packages, which for openEHR is EHR and demographic.
     * - except assumed primitives that are mapped to internal types.
     * = plus base.RMObject which is the 'Any' class
     * - plus base.Interval
     * - plus aom.AuthoredResource // UNCLEAR WHY
     * - plus aom.TranslationDetails // UNCLEAR WHY
     *
     * The result should correspond to the set of classes in the corresponding BMM, minus those
     * classified as primitive. The BMM class set can be generated per the code in
     * RM102ConversionTest.testFindAllNonPrimitiveClasses
     * @param baseClass
     */
    @Override
    protected void addTypes(Class<?> baseClass) {
        addClass(Interval.class); // extra class from the base package. No RMObject because it is also used in the AOM
        addClass(RMObject.class); // Any class for type system
        addClass(AuthoredResource.class);
        addClass(TranslationDetails.class);

        /*
         * The following list is generated by the test S2RmInfoLookupTest.testFindAllClassesUsingClassLoader()
         */
        addClass(AccessControlSettings.class);
        addClass(Accountability.class);
        addClass(Action.class);
        addClass(Activity.class);
        addClass(AddressUse.class);
        addClass(AdminEntry.class);
        addClass(Agent.class);
        addClass(AggregateAgent.class);
        addClass(ArchetypeId.class);
        addClass(Archetyped.class);
        addClass(ArtifactId.class);
        addClass(ArtifactKind.class);
        addClass(Assessment.class);
        addClass(Attestation.class);
        addClass(AttestationReason.class);
        addClass(AuditChangeType.class);
        addClass(AuditDetails.class);
        addClass(AuthoredResource.class);
        addClass(Automaton.class);
        addClass(BillingEncounter.class);
        addClass(BiologicalEntityKind.class);
        addClass(BooleanValue.class);
        addClass(Capability.class);
        addClass(Cardinality.class);
        addClass(CareActEntry.class);
        addClass(CareEntry.class);
        addClass(CareProcess.class);
        addClass(ClockTime.class);
        addClass(CodedOrdinal.class);
        addClass(CodedText.class);
        addClass(CommsAddressUse.class);
        addClass(ComparisonOperator.class);
        addClass(Composition.class);
        addClass(ConsumableUse.class);
        addClass(ContentItem.class);
        addClass(Contribution.class);
        addClass(Count.class);
        addClass(CustomaryTime.class);
        addClass(DataValue.class);
        addClass(DateTimeValue.class);
        addClass(DateValue.class);
        addClass(DayHourSpecifier.class);
        addClass(DirectObservation.class);
        addClass(DurationValue.class);
        addClass(Ehr.class);
        addClass(EhrAccess.class);
        addClass(EhrStatus.class);
        addClass(EhrUriRef.class);
        addClass(Encapsulated.class);
        addClass(EncounterState.class);
        addClass(Entity.class);
        addClass(EntityKind.class);
        addClass(EntityKindRelationship.class);
        addClass(EntityKindRelationshipGroup.class);
        addClass(EntityRefNode.class);
        addClass(EntityRelationship.class);
        addClass(EntityRelationshipGroup.class);
        addClass(Entry.class);
        addClass(EnumerationVar.class);
        addClass(EnumerationVarString.class);
        addClass(Event.class);
        addClass(EventContext.class);
        addClass(FacilityKind.class);
        addClass(FeederAudit.class);
        addClass(FeederAuditDetails.class);
        addClass(FiatLine.class);
        addClass(FiatPoint.class);
        addClass(FiatSurface.class);
        addClass(Folder.class);
        addClass(GeographicalSite.class);
        addClass(HourSpecifier.class);
        addClass(IdUse.class);
        addClass(Imaging.class);
        addClass(ImportedVersion.class);
        addClass(IndependentObjectKind.class);
        addClass(IndirectObservation.class);
        addClass(IndividualAgent.class);
        addClass(InfoItem.class);
        addClass(InfoNode.class);
        addClass(InternetId.class);
        addClass(Interval.class);
        addClass(IntervalEvent.class);
        addClass(IsoOid.class);
        addClass(LabResult.class);
        addClass(LanguageCapability.class);
        addClass(Link.class);
        addClass(Locatable.class);
        addClass(LocationAddressUse.class);
        addClass(MaterialEntity.class);
        addClass(MaterialEntityKind.class);
        addClass(MaterialLocation.class);
        addClass(MateriallyDependentEntityKind.class);
        addClass(Measurable.class);
        addClass(Measured.class);
        addClass(Money.class);
        addClass(Multimedia.class);
        addClass(MultiplicityInterval.class);
        addClass(Node.class);
        addClass(ObjectAggregateKind.class);
        addClass(ObjectExtensionKind.class);
        addClass(ObjectId.class);
        addClass(ObjectRef.class);
        addClass(Observation.class);
        addClass(Occurrence.class);
        addClass(OccurrencePattern.class);
        addClass(OccurrenceTimesSpecifier.class);
        addClass(OccurrentKind.class);
        addClass(Order.class);
        addClass(OrderExecutionState.class);
        addClass(OrderExecutionTransition.class);
        addClass(OrderTracking.class);
        addClass(OrderedDatum.class);
        addClass(OrderedValue.class);
        addClass(OrgEntity.class);
        addClass(OrgUnit.class);
        addClass(Organization.class);
        addClass(OriginalVersion.class);
        addClass(Parsable.class);
        addClass(Participation.class);
        addClass(Party.class);
        addClass(PartyIdentity.class);
        addClass(PartyRelationship.class);
        addClass(Pathable.class);
        addClass(PeriodSpecifier.class);
        addClass(Person.class);
        addClass(Persona.class);
        addClass(PhysicalEntity.class);
        addClass(PhysicalEntityKind.class);
        addClass(PlainText.class);
        addClass(PointEvent.class);
        addClass(PrimitiveId.class);
        addClass(Process.class);
        addClass(ProcessKind.class);
        addClass(Proportion.class);
        addClass(Quantity.class);
        addClass(QuestionnaireResponse.class);
        addClass(Range.class);
        addClass(Ratio.class);
        addClass(ReferenceRange.class);
        addClass(ResourceAnnotations.class);
        addClass(ResourceDescription.class);
        addClass(ResourceDescriptionItem.class);
        addClass(ResourceUse.class);
        addClass(RweIdRef.class);
        addClass(SampleFunctionKind.class);
        addClass(Score.class);
        addClass(Section.class);
        addClass(ServiceEvent.class);
        addClass(ServiceEventState.class);
        addClass(ServiceUse.class);
        addClass(SocialEntity.class);
        addClass(Space.class);
        addClass(SpatialRegion.class);
        addClass(StateTransition.class);
        addClass(SubstanceKind.class);
        addClass(Team.class);
        addClass(TemporalValue.class);
        addClass(TerminologyCode.class);
        addClass(TerminologyId.class);
        addClass(TerminologyTerm.class);
        addClass(Text.class);
        addClass(TimeValue.class);
        addClass(Timing.class);
        addClass(TranslationDetails.class);
        addClass(TrendKind.class);
        addClass(Uri.class);
        addClass(UriRef.class);
        addClass(Uuid.class);
        addClass(ValidityKind.class);
        addClass(Version.class);
        addClass(VersionLifecycleState.class);
        addClass(VersionStatus.class);
        addClass(VersionTreeId.class);
        addClass(VersionedComposition.class);
        addClass(VersionedEhrAccess.class);
        addClass(VersionedEhrStatus.class);
        addClass(VersionedFolder.class);
        addClass(VersionedObject.class);
    }

    public static S2RmInfoLookup getInstance() {
        if(instance == null) {
            instance = new S2RmInfoLookup();
        }
        return instance;
    }

    @Override
    public Class<?> getClassToBeCreated(String rmTypename) {
        if (rmTypename.equals("Event")) {
            //this is an abstract class and cannot be created. Create point event instead
            return PointEvent.class;
        }
        return getClass(rmTypename);
    }

    @Override
    public Object convertToConstraintObject(Object object, CPrimitiveObject<?, ?> cPrimitiveObject) {
        return object;
    }

    @Override
    public void processCreatedObject(Object createdObject, CObject constraint) {
        if (createdObject instanceof Locatable) { //and most often, it will be
            Locatable locatable = (Locatable) createdObject;
            locatable.setArchetypeNodeId(constraint.getNodeId());
            locatable.setName(constraint.getMeaning());
            if (constraint instanceof CArchetypeRoot) {
                CArchetypeRoot root = (CArchetypeRoot) constraint;
                if (root.getArchetypeRef() != null) {
                    Archetyped details = new Archetyped();
                    details.setArchetypeId(new ArchetypeId(root.getArchetypeRef()));
                    details.setRmVersion(RM_VERSION);
                    locatable.setArchetypeDetails(details);
                }
            }
        }
    }

    @Override
    public String getArchetypeNodeIdFromRMObject(Object rmObject) {
        if(rmObject == null) {
            return null;
        }
        if(rmObject instanceof Locatable) {
            Locatable locatable = (Locatable) rmObject;
            return locatable.getArchetypeNodeId();
        }
        return null;
    }

    @Override
    public String getArchetypeIdFromArchetypedRmObject(Object rmObject) {
        if(rmObject instanceof Locatable) {
            Locatable locatable = (Locatable) rmObject;
            if(locatable.getArchetypeDetails() != null) {
                return locatable.getArchetypeDetails().getArchetypeId().getValue();
            }
        }
        return null;
    }

    @Override
    public String getNameFromRMObject(Object rmObject) {
        if(rmObject == null) {
            return null;
        }
        if(rmObject instanceof Locatable) {
            Locatable locatable = (Locatable) rmObject;
            return locatable.getName();
        }
        return null;
    }

    @Override
    public Object clone(Object rmObject) {
        if(rmObject instanceof RMObject) {
            return ((RMObject) rmObject).clone();
        }
        throw new IllegalArgumentException("The ArchieRMInfoLookup can only clone openehr reference model objects");
    }

    /**
     * Notification that a value at a given path has been updated in the given archetype. Perform tasks here to make sure
     * every other paths are updated as well.
     * @param rmObject
     * @param archetype
     * @param pathOfParent
     * @param parent
     */
    @Override
    public Map<String, Object> pathHasBeenUpdated(Object rmObject, Archetype archetype, String pathOfParent, Object parent) {
        return S2RmUpdatedValueHandler.pathHasBeenUpdated(rmObject, archetype, pathOfParent, parent);
    }

    @Override
    public boolean validatePrimitiveType(String rmTypeName, String rmAttributeName, CPrimitiveObject<?, ?> cObject) {
        RMAttributeInfo attributeInfo = this.getAttributeInfo(rmTypeName, rmAttributeName);
        if(attributeInfo == null) {
            return true;//cannot validate
        }
        Class<?> typeInCollection = attributeInfo.getTypeInCollection();
        if(cObject instanceof CInteger) {
            return typeInCollection.equals(Long.class) || typeInCollection.getName().equals("long") ||
                    typeInCollection.getName().equals("int") || EnumerationVarInteger.class.isAssignableFrom(typeInCollection);

        } else if(cObject instanceof CReal) {
            return typeInCollection.equals(BigDecimal.class) || typeInCollection.getName().contains("ecimal") ||
                    typeInCollection.equals(Double.class) || typeInCollection.getName().equals("double");

        } else if(cObject instanceof CString) {
            return typeInCollection.equals(String.class) || EnumerationVarString.class.isAssignableFrom(typeInCollection);

        } else if(cObject instanceof CDate) {
            return typeInCollection.equals(String.class) ||
                    typeInCollection.equals(Date.class);

        } else if(cObject instanceof CDateTime) {
            return typeInCollection.equals(String.class) ||
                    typeInCollection.equals(RmDateTime.class);

        } else if(cObject instanceof CDuration) {
            return typeInCollection.equals(String.class) ||
                    typeInCollection.equals(RmDuration.class);

        } else if(cObject instanceof CTime) {
            return typeInCollection.equals(String.class) ||
                    typeInCollection.equals(RmTime.class);

        } else if(cObject instanceof CTerminologyCode) {
            return typeInCollection.equals(TerminologyCode.class);

        } else if(cObject instanceof CBoolean) {
            return typeInCollection.equals(Boolean.class) || typeInCollection.getName().equals("boolean");
        }
        return false;

    }

    @Override
    public Collection<RMPackageId> getId() {
        List<RMPackageId> result = new ArrayList<>();
        result.add(new RMPackageId("s2", "EHR"));
        result.add(new RMPackageId("s2", "ENTITY"));
        return result;
    }

}

