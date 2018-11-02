/*     */ package com.blzkz.appendtextwithencoding;
/*     */ 
/*     */ import com.appiancorp.suiteapi.content.ContentConstants;
/*     */ import com.appiancorp.suiteapi.content.ContentService;
/*     */ import com.appiancorp.suiteapi.knowledge.Document;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class AppianUtils
/*     */ {
/*  12 */   private static final Logger LOG = Logger.getLogger(AppianUtils.class);
/*     */   
/*     */   public static Document getDocumentFromAppian(ContentService cs, Long documentId) throws Exception {
/*  15 */     Document[] docs = cs.download(documentId, ContentConstants.VERSION_CURRENT, Boolean.valueOf(false));
/*  16 */     if (docs.length > 0) {
/*  17 */       LOG.debug("Document size in k : " + docs[0].getSizeInKB());
/*  18 */       LOG.debug("Document : " + docs[0]);
/*  19 */       LOG.debug("Document Id : " + documentId);
/*     */       
/*  21 */       return docs[0];
/*     */     }
/*  23 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Document createDocumentInAppian(ContentService cs, String filePath, Long destinationFolder, Long destinationDocument, String documentName, String documentDescription)
/*     */     throws Exception
/*     */   {
/*  47 */     String fileName = FilenameUtils.getBaseName(filePath);
/*  48 */     String fileExt = FilenameUtils.getExtension(filePath);
/*     */     
/*  50 */     String docName = FilenameUtils.getBaseName(documentName);
/*  51 */     String docExt = FilenameUtils.getExtension(documentName);
/*     */     
/*     */ 
/*  54 */     if (StringUtils.isBlank(docName)) {
/*  55 */       docName = fileName;
/*     */     }
/*  57 */     if (StringUtils.isBlank(docExt)) {
/*  58 */       docExt = fileExt;
/*     */     }
/*     */     
/*     */ 
/*  62 */     StringUtils.isBlank(documentDescription);
/*     */     
/*     */ 
/*     */ 
/*  66 */     LOG.debug("File Path : " + filePath);
/*  67 */     LOG.debug("Doc Name : " + docName);
/*  68 */     LOG.debug("Doc Extension : " + docExt);
/*     */     
/*  70 */     Document d = null;
/*  71 */     Long documentId = null;
/*     */     
/*     */ 
/*  74 */     if (destinationDocument != null) {
/*  75 */       documentId = destinationDocument;
/*     */       
/*     */ 
/*  78 */       d = getDocumentFromAppian(cs, documentId);
/*  79 */       d.setName(docName);
/*  80 */       d.setExtension(docExt);
/*  81 */       d.setDescription(documentDescription);
/*     */       
/*  83 */       cs.createVersion(d, ContentConstants.UNIQUE_NONE);
/*     */     }
/*     */     else {
/*  86 */       d = new Document(destinationFolder, docName, docExt);
/*  87 */       d.setDescription(documentDescription);
/*  88 */       d.setState(Integer.valueOf(127));
/*     */       
/*     */ 
/*  91 */       documentId = cs.create(d, ContentConstants.UNIQUE_NONE);
/*  92 */       d.setId(documentId);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 104 */     return d;
/*     */   }
/*     */   
/*     */   public static String getInternalFilePath(ContentService cs, Document d) throws Exception {
/* 108 */     String path = cs.getInternalFilename(d.getId());
/*     */     
/* 110 */     if (StringUtils.isBlank(path)) {
/* 111 */       path = d.getInternalFilename();
/*     */     }
/*     */     
/* 114 */     return path;
/*     */   }
/*     */ }


/* Location:              C:\Users\sergio.blazquez\Documents\Plugins\filereader\com.appiancorp.plugins.filereader-2.0.0.jar!\com\appiancorp\ps\plugins\textutilities\AppianUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */