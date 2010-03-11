/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package nu.ted.gen;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.protocol.*;

public class WatchedSeries implements TBase<WatchedSeries._Fields>, java.io.Serializable, Cloneable, Comparable<WatchedSeries> {
  private static final TStruct STRUCT_DESC = new TStruct("WatchedSeries");

  private static final TField U_ID_FIELD_DESC = new TField("uID", TType.STRING, (short)1);
  private static final TField NAME_FIELD_DESC = new TField("name", TType.STRING, (short)2);
  private static final TField SEASON_FIELD_DESC = new TField("season", TType.I16, (short)3);
  private static final TField EPISODE_FIELD_DESC = new TField("episode", TType.I16, (short)4);

  private String uID;
  private String name;
  private short season;
  private short episode;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    U_ID((short)1, "uID"),
    NAME((short)2, "name"),
    SEASON((short)3, "season"),
    EPISODE((short)4, "episode");

    private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byId.put((int)field._thriftId, field);
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      return byId.get(fieldId);
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __SEASON_ISSET_ID = 0;
  private static final int __EPISODE_ISSET_ID = 1;
  private BitSet __isset_bit_vector = new BitSet(2);

  public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
    put(_Fields.U_ID, new FieldMetaData("uID", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.STRING)));
    put(_Fields.NAME, new FieldMetaData("name", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.STRING)));
    put(_Fields.SEASON, new FieldMetaData("season", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I16)));
    put(_Fields.EPISODE, new FieldMetaData("episode", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I16)));
  }});

  static {
    FieldMetaData.addStructMetaDataMap(WatchedSeries.class, metaDataMap);
  }

  public WatchedSeries() {
  }

  public WatchedSeries(
    String uID,
    String name,
    short season,
    short episode)
  {
    this();
    this.uID = uID;
    this.name = name;
    this.season = season;
    setSeasonIsSet(true);
    this.episode = episode;
    setEpisodeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public WatchedSeries(WatchedSeries other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetUID()) {
      this.uID = other.uID;
    }
    if (other.isSetName()) {
      this.name = other.name;
    }
    this.season = other.season;
    this.episode = other.episode;
  }

  public WatchedSeries deepCopy() {
    return new WatchedSeries(this);
  }

  @Deprecated
  public WatchedSeries clone() {
    return new WatchedSeries(this);
  }

  public String getUID() {
    return this.uID;
  }

  public WatchedSeries setUID(String uID) {
    this.uID = uID;
    return this;
  }

  public void unsetUID() {
    this.uID = null;
  }

  /** Returns true if field uID is set (has been asigned a value) and false otherwise */
  public boolean isSetUID() {
    return this.uID != null;
  }

  public void setUIDIsSet(boolean value) {
    if (!value) {
      this.uID = null;
    }
  }

  public String getName() {
    return this.name;
  }

  public WatchedSeries setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been asigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public short getSeason() {
    return this.season;
  }

  public WatchedSeries setSeason(short season) {
    this.season = season;
    setSeasonIsSet(true);
    return this;
  }

  public void unsetSeason() {
    __isset_bit_vector.clear(__SEASON_ISSET_ID);
  }

  /** Returns true if field season is set (has been asigned a value) and false otherwise */
  public boolean isSetSeason() {
    return __isset_bit_vector.get(__SEASON_ISSET_ID);
  }

  public void setSeasonIsSet(boolean value) {
    __isset_bit_vector.set(__SEASON_ISSET_ID, value);
  }

  public short getEpisode() {
    return this.episode;
  }

  public WatchedSeries setEpisode(short episode) {
    this.episode = episode;
    setEpisodeIsSet(true);
    return this;
  }

  public void unsetEpisode() {
    __isset_bit_vector.clear(__EPISODE_ISSET_ID);
  }

  /** Returns true if field episode is set (has been asigned a value) and false otherwise */
  public boolean isSetEpisode() {
    return __isset_bit_vector.get(__EPISODE_ISSET_ID);
  }

  public void setEpisodeIsSet(boolean value) {
    __isset_bit_vector.set(__EPISODE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case U_ID:
      if (value == null) {
        unsetUID();
      } else {
        setUID((String)value);
      }
      break;

    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case SEASON:
      if (value == null) {
        unsetSeason();
      } else {
        setSeason((Short)value);
      }
      break;

    case EPISODE:
      if (value == null) {
        unsetEpisode();
      } else {
        setEpisode((Short)value);
      }
      break;

    }
  }

  public void setFieldValue(int fieldID, Object value) {
    setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case U_ID:
      return getUID();

    case NAME:
      return getName();

    case SEASON:
      return new Short(getSeason());

    case EPISODE:
      return new Short(getEpisode());

    }
    throw new IllegalStateException();
  }

  public Object getFieldValue(int fieldId) {
    return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    switch (field) {
    case U_ID:
      return isSetUID();
    case NAME:
      return isSetName();
    case SEASON:
      return isSetSeason();
    case EPISODE:
      return isSetEpisode();
    }
    throw new IllegalStateException();
  }

  public boolean isSet(int fieldID) {
    return isSet(_Fields.findByThriftIdOrThrow(fieldID));
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof WatchedSeries)
      return this.equals((WatchedSeries)that);
    return false;
  }

  public boolean equals(WatchedSeries that) {
    if (that == null)
      return false;

    boolean this_present_uID = true && this.isSetUID();
    boolean that_present_uID = true && that.isSetUID();
    if (this_present_uID || that_present_uID) {
      if (!(this_present_uID && that_present_uID))
        return false;
      if (!this.uID.equals(that.uID))
        return false;
    }

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_season = true;
    boolean that_present_season = true;
    if (this_present_season || that_present_season) {
      if (!(this_present_season && that_present_season))
        return false;
      if (this.season != that.season)
        return false;
    }

    boolean this_present_episode = true;
    boolean that_present_episode = true;
    if (this_present_episode || that_present_episode) {
      if (!(this_present_episode && that_present_episode))
        return false;
      if (this.episode != that.episode)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(WatchedSeries other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    WatchedSeries typedOther = (WatchedSeries)other;

    lastComparison = Boolean.valueOf(isSetUID()).compareTo(isSetUID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(uID, typedOther.uID);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetName()).compareTo(isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(name, typedOther.name);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetSeason()).compareTo(isSetSeason());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(season, typedOther.season);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetEpisode()).compareTo(isSetEpisode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(episode, typedOther.episode);
    if (lastComparison != 0) {
      return lastComparison;
    }
    return 0;
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      _Fields fieldId = _Fields.findByThriftId(field.id);
      if (fieldId == null) {
        TProtocolUtil.skip(iprot, field.type);
      } else {
        switch (fieldId) {
          case U_ID:
            if (field.type == TType.STRING) {
              this.uID = iprot.readString();
            } else { 
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
          case NAME:
            if (field.type == TType.STRING) {
              this.name = iprot.readString();
            } else { 
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
          case SEASON:
            if (field.type == TType.I16) {
              this.season = iprot.readI16();
              setSeasonIsSet(true);
            } else { 
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
          case EPISODE:
            if (field.type == TType.I16) {
              this.episode = iprot.readI16();
              setEpisodeIsSet(true);
            } else { 
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
        }
        iprot.readFieldEnd();
      }
    }
    iprot.readStructEnd();
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.uID != null) {
      oprot.writeFieldBegin(U_ID_FIELD_DESC);
      oprot.writeString(this.uID);
      oprot.writeFieldEnd();
    }
    if (this.name != null) {
      oprot.writeFieldBegin(NAME_FIELD_DESC);
      oprot.writeString(this.name);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldBegin(SEASON_FIELD_DESC);
    oprot.writeI16(this.season);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(EPISODE_FIELD_DESC);
    oprot.writeI16(this.episode);
    oprot.writeFieldEnd();
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("WatchedSeries(");
    boolean first = true;

    sb.append("uID:");
    if (this.uID == null) {
      sb.append("null");
    } else {
      sb.append(this.uID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("season:");
    sb.append(this.season);
    first = false;
    if (!first) sb.append(", ");
    sb.append("episode:");
    sb.append(this.episode);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }

}

