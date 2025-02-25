// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package com.taotao.cloud.netty.proto;

/**
 * Protobuf type {@code  StreamResponse}
 */
public final class StreamResponse extends
        com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements: StreamResponse)
        StreamResponseOrBuilder {
    // Use StreamResponse.newBuilder() to construct.
    private StreamResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private StreamResponse() {
        responseInfo_ = "";
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
        return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }

    private StreamResponse(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        this();
        int mutable_bitField0_ = 0;
        try {
            boolean done = false;
            while (!done) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        done = true;
                        break;
                    default: {
                        if (!input.skipField(tag)) {
                            done = true;
                        }
                        break;
                    }
                    case 10: {
                        String s = input.readStringRequireUtf8();

                        responseInfo_ = s;
                        break;
                    }
                }
            }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        } catch (java.io.IOException e) {
            throw new com.google.protobuf.InvalidProtocolBufferException(
                    e).setUnfinishedMessage(this);
        } finally {
            makeExtensionsImmutable();
        }
    }

    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
        return  StudentProto.internal_static_com_shengsiyuan_proto_StreamResponse_descriptor;
    }

    @Override
	protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
    internalGetFieldAccessorTable() {
        return  StudentProto.internal_static_com_shengsiyuan_proto_StreamResponse_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                         StreamResponse.class,  StreamResponse.Builder.class);
    }

    public static final int RESPONSE_INFO_FIELD_NUMBER = 1;
    private volatile Object responseInfo_;

    /**
     * <code>string response_info = 1;</code>
     */
    @Override
	public String getResponseInfo() {
        Object ref = responseInfo_;
        if (ref instanceof String) {
            return (String) ref;
        } else {
            com.google.protobuf.ByteString bs =
                    (com.google.protobuf.ByteString) ref;
            String s = bs.toStringUtf8();
            responseInfo_ = s;
            return s;
        }
    }

    /**
     * <code>string response_info = 1;</code>
     */
    @Override
	public com.google.protobuf.ByteString
    getResponseInfoBytes() {
        Object ref = responseInfo_;
        if (ref instanceof String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8(
                            (String) ref);
            responseInfo_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    private byte memoizedIsInitialized = -1;

    @Override
	public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) {
			return true;
		}
        if (isInitialized == 0) {
			return false;
		}

        memoizedIsInitialized = 1;
        return true;
    }

    @Override
	public void writeTo(com.google.protobuf.CodedOutputStream output)
            throws java.io.IOException {
        if (!getResponseInfoBytes().isEmpty()) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 1, responseInfo_);
        }
    }

    @Override
	public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
			return size;
		}

        size = 0;
        if (!getResponseInfoBytes().isEmpty()) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, responseInfo_);
        }
        memoizedSize = size;
        return size;
    }

    private static final long serialVersionUID = 0L;

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof  StreamResponse)) {
            return super.equals(obj);
        }
         StreamResponse other = ( StreamResponse) obj;

        boolean result = true;
        result = result && getResponseInfo()
                .equals(other.getResponseInfo());
        return result;
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + RESPONSE_INFO_FIELD_NUMBER;
        hash = (53 * hash) + getResponseInfo().hashCode();
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static  StreamResponse parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static  StreamResponse parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static  StreamResponse parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static  StreamResponse parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static  StreamResponse parseFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static  StreamResponse parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static  StreamResponse parseDelimitedFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input);
    }

    public static  StreamResponse parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static  StreamResponse parseFrom(
            com.google.protobuf.CodedInputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static  StreamResponse parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
	public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder( StreamResponse prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    @Override
	public Builder toBuilder() {
        return this == DEFAULT_INSTANCE
                ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /**
     * Protobuf type {@code  StreamResponse}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements: StreamResponse)
             StreamResponseOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return  StudentProto.internal_static_com_shengsiyuan_proto_StreamResponse_descriptor;
        }

        @Override
		protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return  StudentProto.internal_static_com_shengsiyuan_proto_StreamResponse_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                             StreamResponse.class,  StreamResponse.Builder.class);
        }

        // Construct using  StreamResponse.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3
                    .alwaysUseFieldBuilders) {
            }
        }

        @Override
		public Builder clear() {
            super.clear();
            responseInfo_ = "";

            return this;
        }

        @Override
		public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
            return  StudentProto.internal_static_com_shengsiyuan_proto_StreamResponse_descriptor;
        }

        @Override
		public  StreamResponse getDefaultInstanceForType() {
            return  StreamResponse.getDefaultInstance();
        }

        @Override
		public  StreamResponse build() {
             StreamResponse result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @Override
		public  StreamResponse buildPartial() {
             StreamResponse result = new  StreamResponse(this);
            result.responseInfo_ = responseInfo_;
            onBuilt();
            return result;
        }

        @Override
		public Builder clone() {
            return (Builder) super.clone();
        }

        @Override
		public Builder setField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                Object value) {
            return (Builder) super.setField(field, value);
        }

        @Override
		public Builder clearField(
                com.google.protobuf.Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        @Override
		public Builder clearOneof(
                com.google.protobuf.Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        @Override
		public Builder setRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        @Override
		public Builder addRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        @Override
		public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof  StreamResponse) {
                return mergeFrom(( StreamResponse) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom( StreamResponse other) {
            if (other ==  StreamResponse.getDefaultInstance()) {
				return this;
			}
            if (!other.getResponseInfo().isEmpty()) {
                responseInfo_ = other.responseInfo_;
                onChanged();
            }
            onChanged();
            return this;
        }

        @Override
		public final boolean isInitialized() {
            return true;
        }

        @Override
		public Builder mergeFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
             StreamResponse parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                parsedMessage = ( StreamResponse) e.getUnfinishedMessage();
                throw e.unwrapIOException();
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private Object responseInfo_ = "";

        /**
         * <code>string response_info = 1;</code>
         */
        @Override
		public String getResponseInfo() {
            Object ref = responseInfo_;
            if (!(ref instanceof String)) {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                responseInfo_ = s;
                return s;
            } else {
                return (String) ref;
            }
        }

        /**
         * <code>string response_info = 1;</code>
         */
        @Override
		public com.google.protobuf.ByteString
        getResponseInfoBytes() {
            Object ref = responseInfo_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (String) ref);
                responseInfo_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string response_info = 1;</code>
         */
        public Builder setResponseInfo(
                String value) {
            if (value == null) {
                throw new NullPointerException();
            }

            responseInfo_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>string response_info = 1;</code>
         */
        public Builder clearResponseInfo() {

            responseInfo_ = getDefaultInstance().getResponseInfo();
            onChanged();
            return this;
        }

        /**
         * <code>string response_info = 1;</code>
         */
        public Builder setResponseInfoBytes(
                com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);

            responseInfo_ = value;
            onChanged();
            return this;
        }

        @Override
		public final Builder setUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return this;
        }

        @Override
		public final Builder mergeUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return this;
        }


        // @@protoc_insertion_point(builder_scope: StreamResponse)
    }

    // @@protoc_insertion_point(class_scope: StreamResponse)
    private static final  StreamResponse DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new  StreamResponse();
    }

    public static  StreamResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<StreamResponse>
            PARSER = new com.google.protobuf.AbstractParser<StreamResponse>() {
        @Override
		public StreamResponse parsePartialFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return new StreamResponse(input, extensionRegistry);
        }
    };

    public static com.google.protobuf.Parser<StreamResponse> parser() {
        return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<StreamResponse> getParserForType() {
        return PARSER;
    }

    @Override
	public  StreamResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}

