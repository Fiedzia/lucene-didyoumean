package org.apache.lucene.index.facade;
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

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.index.IndexReader;

import java.io.IOException;

/**
 * @author karl wettin <mailto:karl.wettin@gmail.com>
 * Date: 2007-aug-17
 * Time: 00:17:14
 */
public class DirectoryIndexFacade extends IndexFacade {

  private Directory directory;
  private IndexWriter.MaxFieldLength mfl;

  public DirectoryIndexFacade(Directory directory) throws IOException {
    this(directory, IndexWriter.MaxFieldLength.LIMITED);
  }

  public DirectoryIndexFacade(Directory directory,
                              IndexWriter.MaxFieldLength mfl)
                                                            throws IOException {
    this.directory = directory;
    this.mfl = mfl;
  }

  @Override
  public IndexReader indexReaderFactory() throws IOException {
    return IndexReader.open(directory);
  }

  @Override
  public IndexWriterFacade indexWriterFactory(Analyzer analyzer,
                                              boolean create)
                                                            throws IOException {
    return new DirectoryIndexWriterFacade(directory, analyzer, create, mfl);
  }

  @Override
  public void close() throws IOException {
      directory.close();
  }
}
