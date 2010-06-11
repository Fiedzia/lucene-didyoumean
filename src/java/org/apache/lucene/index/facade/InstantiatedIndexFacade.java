package org.apache.lucene.index.facade;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.instantiated.InstantiatedIndex;
import org.apache.lucene.store.instantiated.InstantiatedIndexWriter;
import org.apache.lucene.document.Document;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;

import java.io.IOException;
/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */


/**
 * @author karl wettin <mailto:karl.wettin@gmail.com>
 * Date: 2007-aug-17
 * Time: 03:51:57
 */
public class InstantiatedIndexFacade extends IndexFacade {

  private InstantiatedIndex ii;
  private IndexWriter.MaxFieldLength mfl;

  public InstantiatedIndexFacade(InstantiatedIndex ii, IndexWriter.MaxFieldLength mfl) {
    this.ii = ii;
    this.mfl = mfl;
  }

  public InstantiatedIndexFacade(InstantiatedIndex ii) {
    this(ii, IndexWriter.MaxFieldLength.LIMITED);
  }

  public IndexReader indexReaderFactory() throws IOException {
    return ii.indexReaderFactory();
  }

  public IndexWriterFacade indexWriterFactory(final Analyzer analyzer, final boolean create) throws IOException {
    return new IndexWriterFacade() {

      private InstantiatedIndexWriter iw = ii.indexWriterFactory(analyzer, create);

      public void addDocument(Document document, Analyzer analyzer) throws IOException {
        iw.addDocument(document, analyzer);
      }

      public void addDocument(Document document) throws IOException {
        iw.addDocument(document);
      }

      public void close() throws IOException {
        iw.close();
      }

      public void commit() throws IOException {
        iw.commit();
      }

      public void optimize() throws IOException {

      }
    };
  }

  public void close() throws IOException {
      ii.close();
  }
}
