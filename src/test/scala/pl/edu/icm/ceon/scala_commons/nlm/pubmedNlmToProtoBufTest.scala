/*
 * Copyright (c) 2013-2013 ICM UW
 */

package pl.edu.icm.ceon.scala_commons.nlm

import org.junit.Assert._
import pl.edu.icm.coansys.models.DocumentProtos.DocumentMetadata
import nlmToDocumentProto.pubmedNlmToProtoBuf
import org.junit.{Test, BeforeClass}
import scala.collection.JavaConverters._

/**
 * @author Mateusz Fedoryszak (m.fedoryszak@icm.edu.pl)
 */
object pubmedNlmToProtoBufTest {
  var doc: DocumentMetadata = null

  @BeforeClass
  def setUp() {
    doc = pubmedNlmToProtoBuf(this.getClass.getResourceAsStream("/pl/edu/icm/ceon/scala_commons/nlm/sample.nxml"))
      .getDocumentMetadata
  }
}

/**
 * @author Mateusz Fedoryszak (m.fedoryszak@icm.edu.pl)
 */
class pubmedNlmToProtoBufTest {

  import pubmedNlmToProtoBufTest.doc

  @Test
  def extIdTest() {
    assertEquals(doc.getExtIdCount, 4)
    assertEquals(doc.getExtId(0).getKey, "pmc")
    assertEquals(doc.getExtId(0).getValue, "2751467")
    assertEquals(doc.getExtId(1).getKey, "pmid")
    assertEquals(doc.getExtId(1).getValue, "18446519")
    assertEquals(doc.getExtId(2).getKey, "publisher-id")
    assertEquals(doc.getExtId(2).getValue, "9022")
    assertEquals(doc.getExtId(3).getKey, "doi")
    assertEquals(doc.getExtId(3).getValue, "10.1208/s12248-008-9022-y")
  }

  @Test
  def abstractTest() {
    assertEquals(doc.getDocumentAbstractCount, 1)
    assertEquals(doc.getDocumentAbstract(0).getLanguage, "en")
    assertTrue(doc.getDocumentAbstract(0).getText.startsWith(
      """SLC5A8 and SLC5A12 are sodium-coupled monocarboxylate transporters (SMCTs), the former"""))
  }

  @Test
  def doiTest() {
    assertEquals(doc.getBasicMetadata.getDoi, "10.1208/s12248-008-9022-y")
  }

  @Test
  def pagesTest() {
    assertEquals(doc.getBasicMetadata.getPages, "193-199")
  }

  @Test
  def volumeTest() {
    assertEquals(doc.getBasicMetadata.getVolume, "10")
  }

  @Test
  def issueTest() {
    assertEquals(doc.getBasicMetadata.getIssue, "1")
  }

  @Test
  def yearTest() {
    assertEquals(doc.getBasicMetadata.getYear, "2008")
  }

  @Test
  def journalTest() {
    assertEquals(doc.getBasicMetadata.getJournal, "The AAPS Journal")
  }

  @Test
  def titleTest() {
    assertEquals(doc.getBasicMetadata.getTitle(0).getText, "Sodium-coupled Monocarboxylate Transporters in Normal Tissues and in Cancer")
  }

  @Test
  def authorTest() {
    assertEquals(doc.getBasicMetadata.getAuthorCount, 7)
    assertEquals(doc.getBasicMetadata.getAuthor(0).getSurname, "Ganapathy")
    assertEquals(doc.getBasicMetadata.getAuthor(0).getForenames, "Vadivel")
  }

  @Test
  def keywordsTest() {
    assertEquals(doc.getKeywords(0).getKeywordsList.asScala, List("gamma-hydroxybutyrate", "kidney/intestine", "monocarboxylate drugs", "SLC5A8/SLC5A12", "tumor suppressor"))
  }

  @Test
  def refTest() {
    assertEquals(doc.getReferenceCount, 35)
    val ref = doc.getReference(0)
    assertEquals(ref.getPosition, 1)
    assertEquals(ref.getBasicMetadata.getAuthorCount, 2)
    assertEquals(ref.getBasicMetadata.getAuthor(0).getSurname, "Enerson")
    assertEquals(ref.getBasicMetadata.getAuthor(0).getForenames, "B. E.")
    assertEquals(ref.getBasicMetadata.getAuthor(1).getSurname, "Drewes")
    assertEquals(ref.getBasicMetadata.getAuthor(1).getForenames, "L. R.")
    assertEquals(ref.getBasicMetadata.getTitle(0).getText,
      "Molecular features, regulation, and function of monocarboxylate transporters: implications for drug delivery")
    assertEquals(ref.getBasicMetadata.getJournal, "J. Pharm. Sci")
    assertEquals(ref.getBasicMetadata.getYear, "2003")
    assertEquals(ref.getBasicMetadata.getVolume, "92")
    assertEquals(ref.getBasicMetadata.getPages, "1531-1544")
    assertEquals(ref.getBasicMetadata.getDoi, "10.1002/jps.10389")
  }
}
