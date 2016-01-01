/**
 * 
 */
package com.healthline.storage.impl;

import java.io.File;
import java.util.Properties;

import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.blobstore.domain.Blob;
import org.jclouds.filesystem.reference.FilesystemConstants;

import com.healthline.storage.api.ISecuredDocumentStorageGateway;

/**
 * @author Aniket
 * 
 */
public class DocumentStorageGatewayImpl
        implements ISecuredDocumentStorageGateway
{

    private String containerName;
    private String storageType;
    private String storageAccount;
    private String primaryAccessKey;

    private String filename = System.getProperty("JAVA_HOME")
                                    + "/lib/security/cacerts".replace('/', File.separatorChar);

    private enum Storage
    {
        FILE_SYSTEM("filesystem");

        private String type;

        private Storage(String type)
        {
            this.type = type;
        }

        public String getType()
        {
            return this.type;
        }
    }

    private static final String FORWARD_SLASH = "/";

    /*
     * (non-Javadoc)
     * @see com.healthline.storage.api.IDocumentStorageGateway#storeFile(java.lang.String, byte[])
     */
    @Override
    public String storeFile(String fileName, byte[] content)
    {
        BlobStoreContext context = getBlobStoreContext();
        BlobStore blobStore = context.getBlobStore();
        Blob blob = blobStore.blobBuilder(fileName).payload(content).build(); //$NON-NLS-1$
        blobStore.putBlob(this.containerName, blob);
        context.close();
        return getFileURI(fileName, this.containerName);

    }

    /*
     * (non-Javadoc)
     * @see com.healthline.storage.api.IDocumentStorageGateway#retrieveFile(java.lang.String)
     */
    @Override
    public String retrieveFile(String url)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.storage.api.ISecuredDocumentStorageGateway#encryptAndStoreFile(java.lang.String, byte[], java.lang.String)
     */
    @Override
    public String encryptAndStoreFile(String fileName, byte[] content, String password)
    {
        // TODO encrypt file
        return storeFile(fileName, content);
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.storage.api.ISecuredDocumentStorageGateway#retrieveAndDescryptFile(java.lang.String, java.lang.String)
     */
    @Override
    public String retrieveAndDescryptFile(String url, String password)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
     * @return BlobStoreContext depending upon the provider / storage type set
     * @throws Exception
     */
    private BlobStoreContext getBlobStoreContext()
    {
        // Load default trust store from java
        System.setProperty("javax.net.ssl.trustStore", this.filename);
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        BlobStoreContext context = null;
        if ( Storage.FILE_SYSTEM.getType().equals(this.storageType) )
        {
            Properties properties = new Properties();
            properties.setProperty(FilesystemConstants.PROPERTY_BASEDIR, this.storageAccount);
            properties.setProperty("jclouds.credential", this.primaryAccessKey);
            context = ContextBuilder.newBuilder(Storage.FILE_SYSTEM.getType()).overrides(properties)
                    .buildView(BlobStoreContext.class);
        }
        return context;
    }

    private String getFileURI(String fileName, String container)
    {
        return FORWARD_SLASH + container + FORWARD_SLASH + fileName;
    }

    /**
     * @param containerName the containerName to set
     */
    public void setContainerName(String containerName)
    {
        this.containerName = containerName;
    }

    /**
     * @param storageType the storageType to set
     */
    public void setStorageType(String storageType)
    {
        this.storageType = storageType;
    }

    /**
     * @param storageAccount the storageAccount to set
     */
    public void setStorageAccount(String storageAccount)
    {
        this.storageAccount = storageAccount;
    }

    /**
     * @param primaryAccessKey the primaryAccessKey to set
     */
    public void setPrimaryAccessKey(String primaryAccessKey)
    {
        this.primaryAccessKey = primaryAccessKey;
    }
}
