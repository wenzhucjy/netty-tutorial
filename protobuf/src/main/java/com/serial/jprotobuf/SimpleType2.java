// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: simple_type2.proto

package com.serial.jprotobuf;

public final class SimpleType2 {
  private SimpleType2() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface SimpleTypeOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required string name = 1;
    /**
     * <code>required string name = 1;</code>
     *
     * <pre>
     * 名称
     * </pre>
     */
    boolean hasName();
    /**
     * <code>required string name = 1;</code>
     *
     * <pre>
     * 名称
     * </pre>
     */
    java.lang.String getName();
    /**
     * <code>required string name = 1;</code>
     *
     * <pre>
     * 名称
     * </pre>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    // required int32 value = 2;
    /**
     * <code>required int32 value = 2;</code>
     *
     * <pre>
     * value
     * </pre>
     */
    boolean hasValue();
    /**
     * <code>required int32 value = 2;</code>
     *
     * <pre>
     * value
     * </pre>
     */
    int getValue();

    // optional .SimpleType.EnumAttrPOJO attrPojo = 3 [default = STRING];
    /**
     * <code>optional .SimpleType.EnumAttrPOJO attrPojo = 3 [default = STRING];</code>
     */
    boolean hasAttrPojo();
    /**
     * <code>optional .SimpleType.EnumAttrPOJO attrPojo = 3 [default = STRING];</code>
     */
    com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO getAttrPojo();
  }
  /**
   * Protobuf type {@code SimpleType}
   */
  public static final class SimpleType extends
      com.google.protobuf.GeneratedMessage
      implements SimpleTypeOrBuilder {
    // Use SimpleType.newBuilder() to construct.
    private SimpleType(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private SimpleType(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final SimpleType defaultInstance;
    public static SimpleType getDefaultInstance() {
      return defaultInstance;
    }

    public SimpleType getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private SimpleType(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              name_ = input.readBytes();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              value_ = input.readInt32();
              break;
            }
            case 24: {
              int rawValue = input.readEnum();
              com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO value = com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(3, rawValue);
              } else {
                bitField0_ |= 0x00000004;
                attrPojo_ = value;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.serial.jprotobuf.SimpleType2.internal_static_SimpleType_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.serial.jprotobuf.SimpleType2.internal_static_SimpleType_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.serial.jprotobuf.SimpleType2.SimpleType.class, com.serial.jprotobuf.SimpleType2.SimpleType.Builder.class);
    }

    public static com.google.protobuf.Parser<SimpleType> PARSER =
        new com.google.protobuf.AbstractParser<SimpleType>() {
      public SimpleType parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SimpleType(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<SimpleType> getParserForType() {
      return PARSER;
    }

    /**
     * Protobuf enum {@code SimpleType.EnumAttrPOJO}
     */
    public enum EnumAttrPOJO
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <code>STRING = 100;</code>
       */
      STRING(0, 100),
      /**
       * <code>INT = 50;</code>
       */
      INT(1, 50),
      ;

      /**
       * <code>STRING = 100;</code>
       */
      public static final int STRING_VALUE = 100;
      /**
       * <code>INT = 50;</code>
       */
      public static final int INT_VALUE = 50;


      public final int getNumber() { return value; }

      public static EnumAttrPOJO valueOf(int value) {
        switch (value) {
          case 100: return STRING;
          case 50: return INT;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<EnumAttrPOJO>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static com.google.protobuf.Internal.EnumLiteMap<EnumAttrPOJO>
          internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<EnumAttrPOJO>() {
              public EnumAttrPOJO findValueByNumber(int number) {
                return EnumAttrPOJO.valueOf(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(index);
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return com.serial.jprotobuf.SimpleType2.SimpleType.getDescriptor().getEnumTypes().get(0);
      }

      private static final EnumAttrPOJO[] VALUES = values();

      public static EnumAttrPOJO valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        return VALUES[desc.getIndex()];
      }

      private final int index;
      private final int value;

      private EnumAttrPOJO(int index, int value) {
        this.index = index;
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:SimpleType.EnumAttrPOJO)
    }

    private int bitField0_;
    // required string name = 1;
    public static final int NAME_FIELD_NUMBER = 1;
    private java.lang.Object name_;
    /**
     * <code>required string name = 1;</code>
     *
     * <pre>
     * 名称
     * </pre>
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string name = 1;</code>
     *
     * <pre>
     * 名称
     * </pre>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          name_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string name = 1;</code>
     *
     * <pre>
     * 名称
     * </pre>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required int32 value = 2;
    public static final int VALUE_FIELD_NUMBER = 2;
    private int value_;
    /**
     * <code>required int32 value = 2;</code>
     *
     * <pre>
     * value
     * </pre>
     */
    public boolean hasValue() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 value = 2;</code>
     *
     * <pre>
     * value
     * </pre>
     */
    public int getValue() {
      return value_;
    }

    // optional .SimpleType.EnumAttrPOJO attrPojo = 3 [default = STRING];
    public static final int ATTRPOJO_FIELD_NUMBER = 3;
    private com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO attrPojo_;
    /**
     * <code>optional .SimpleType.EnumAttrPOJO attrPojo = 3 [default = STRING];</code>
     */
    public boolean hasAttrPojo() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional .SimpleType.EnumAttrPOJO attrPojo = 3 [default = STRING];</code>
     */
    public com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO getAttrPojo() {
      return attrPojo_;
    }

    private void initFields() {
      name_ = "";
      value_ = 0;
      attrPojo_ = com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO.STRING;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasValue()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getNameBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, value_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeEnum(3, attrPojo_.getNumber());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getNameBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, value_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(3, attrPojo_.getNumber());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.serial.jprotobuf.SimpleType2.SimpleType parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.serial.jprotobuf.SimpleType2.SimpleType parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.serial.jprotobuf.SimpleType2.SimpleType parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.serial.jprotobuf.SimpleType2.SimpleType parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.serial.jprotobuf.SimpleType2.SimpleType parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.serial.jprotobuf.SimpleType2.SimpleType parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.serial.jprotobuf.SimpleType2.SimpleType parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.serial.jprotobuf.SimpleType2.SimpleType parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.serial.jprotobuf.SimpleType2.SimpleType parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.serial.jprotobuf.SimpleType2.SimpleType parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.serial.jprotobuf.SimpleType2.SimpleType prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code SimpleType}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.serial.jprotobuf.SimpleType2.SimpleTypeOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.serial.jprotobuf.SimpleType2.internal_static_SimpleType_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.serial.jprotobuf.SimpleType2.internal_static_SimpleType_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.serial.jprotobuf.SimpleType2.SimpleType.class, com.serial.jprotobuf.SimpleType2.SimpleType.Builder.class);
      }

      // Construct using com.serial.jprotobuf.SimpleType2.SimpleType.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        name_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        value_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        attrPojo_ = com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO.STRING;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.serial.jprotobuf.SimpleType2.internal_static_SimpleType_descriptor;
      }

      public com.serial.jprotobuf.SimpleType2.SimpleType getDefaultInstanceForType() {
        return com.serial.jprotobuf.SimpleType2.SimpleType.getDefaultInstance();
      }

      public com.serial.jprotobuf.SimpleType2.SimpleType build() {
        com.serial.jprotobuf.SimpleType2.SimpleType result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.serial.jprotobuf.SimpleType2.SimpleType buildPartial() {
        com.serial.jprotobuf.SimpleType2.SimpleType result = new com.serial.jprotobuf.SimpleType2.SimpleType(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.name_ = name_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.value_ = value_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.attrPojo_ = attrPojo_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.serial.jprotobuf.SimpleType2.SimpleType) {
          return mergeFrom((com.serial.jprotobuf.SimpleType2.SimpleType)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.serial.jprotobuf.SimpleType2.SimpleType other) {
        if (other == com.serial.jprotobuf.SimpleType2.SimpleType.getDefaultInstance()) return this;
        if (other.hasName()) {
          bitField0_ |= 0x00000001;
          name_ = other.name_;
          onChanged();
        }
        if (other.hasValue()) {
          setValue(other.getValue());
        }
        if (other.hasAttrPojo()) {
          setAttrPojo(other.getAttrPojo());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasName()) {
          
          return false;
        }
        if (!hasValue()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.serial.jprotobuf.SimpleType2.SimpleType parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.serial.jprotobuf.SimpleType2.SimpleType) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required string name = 1;
      private java.lang.Object name_ = "";
      /**
       * <code>required string name = 1;</code>
       *
       * <pre>
       * 名称
       * </pre>
       */
      public boolean hasName() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string name = 1;</code>
       *
       * <pre>
       * 名称
       * </pre>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string name = 1;</code>
       *
       * <pre>
       * 名称
       * </pre>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string name = 1;</code>
       *
       * <pre>
       * 名称
       * </pre>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 1;</code>
       *
       * <pre>
       * 名称
       * </pre>
       */
      public Builder clearName() {
        bitField0_ = (bitField0_ & ~0x00000001);
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 1;</code>
       *
       * <pre>
       * 名称
       * </pre>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        name_ = value;
        onChanged();
        return this;
      }

      // required int32 value = 2;
      private int value_ ;
      /**
       * <code>required int32 value = 2;</code>
       *
       * <pre>
       * value
       * </pre>
       */
      public boolean hasValue() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 value = 2;</code>
       *
       * <pre>
       * value
       * </pre>
       */
      public int getValue() {
        return value_;
      }
      /**
       * <code>required int32 value = 2;</code>
       *
       * <pre>
       * value
       * </pre>
       */
      public Builder setValue(int value) {
        bitField0_ |= 0x00000002;
        value_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 value = 2;</code>
       *
       * <pre>
       * value
       * </pre>
       */
      public Builder clearValue() {
        bitField0_ = (bitField0_ & ~0x00000002);
        value_ = 0;
        onChanged();
        return this;
      }

      // optional .SimpleType.EnumAttrPOJO attrPojo = 3 [default = STRING];
      private com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO attrPojo_ = com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO.STRING;
      /**
       * <code>optional .SimpleType.EnumAttrPOJO attrPojo = 3 [default = STRING];</code>
       */
      public boolean hasAttrPojo() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional .SimpleType.EnumAttrPOJO attrPojo = 3 [default = STRING];</code>
       */
      public com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO getAttrPojo() {
        return attrPojo_;
      }
      /**
       * <code>optional .SimpleType.EnumAttrPOJO attrPojo = 3 [default = STRING];</code>
       */
      public Builder setAttrPojo(com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000004;
        attrPojo_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional .SimpleType.EnumAttrPOJO attrPojo = 3 [default = STRING];</code>
       */
      public Builder clearAttrPojo() {
        bitField0_ = (bitField0_ & ~0x00000004);
        attrPojo_ = com.serial.jprotobuf.SimpleType2.SimpleType.EnumAttrPOJO.STRING;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:SimpleType)
    }

    static {
      defaultInstance = new SimpleType(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:SimpleType)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_SimpleType_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_SimpleType_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022simple_type2.proto\"\202\001\n\nSimpleType\022\014\n\004n" +
      "ame\030\001 \002(\t\022\r\n\005value\030\002 \002(\005\0222\n\010attrPojo\030\003 \001" +
      "(\0162\030.SimpleType.EnumAttrPOJO:\006STRING\"#\n\014" +
      "EnumAttrPOJO\022\n\n\006STRING\020d\022\007\n\003INT\0202B\026\n\024com" +
      ".serial.jprotobuf"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_SimpleType_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_SimpleType_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_SimpleType_descriptor,
              new java.lang.String[] { "Name", "Value", "AttrPojo", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
