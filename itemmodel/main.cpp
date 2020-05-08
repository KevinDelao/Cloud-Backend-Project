/****************************************************************************
**
** Copyright (C) 2016 The Qt Company Ltd.
** Contact: https://www.qt.io/licensing/
**
** This file is part of the Qt Data Visualization module of the Qt Toolkit.
**
** $QT_BEGIN_LICENSE:GPL$
** Commercial License Usage
** Licensees holding valid commercial Qt licenses may use this file in
** accordance with the commercial license agreement provided with the
** Software or, alternatively, in accordance with the terms contained in
** a written agreement between you and The Qt Company. For licensing terms
** and conditions see https://www.qt.io/terms-conditions. For further
** information use the contact form at https://www.qt.io/contact-us.
**
** GNU General Public License Usage
** Alternatively, this file may be used under the terms of the GNU
** General Public License version 3 or (at your option) any later version
** approved by the KDE Free Qt Foundation. The licenses are as published by
** the Free Software Foundation and appearing in the file LICENSE.GPL3
** included in the packaging of this file. Please review the following
** information to ensure the GNU General Public License requirements will
** be met: https://www.gnu.org/licenses/gpl-3.0.html.
**
** $QT_END_LICENSE$
**
****************************************************************************/
/**************************************************************************
**Cloud-Backened-Project -  GUI development by Nicholas Martin 
**The purpose of this Graphical User Interface is to allow for ease of data acquistion as well as data analysis
**for the input of coordinate data sourced from the HTC Vive Virtual Reality Headset. The GUI shall provide the
**following capabilities:
**Real-time data feed of Coordinate data 
**Populate real-time data into Table widget
**Activate/Deactivate main code using button widgets
**Save data files to specific file directories
**Implement Graph widgets
**
**Due to events related to COVID-19 the development process had slowed rapidly. The currently
**does not function completely, however, coupled wiht the documentation provided 
**it shall serve as a satisfactoy baseline for future GUI development.
***************************************************************************/

#include <QtDataVisualization/q3dbars.h>
#include <QtDataVisualization/qcategory3daxis.h>
#include <QtDataVisualization/qitemmodelbardataproxy.h>
#include <QtDataVisualization/qvalue3daxis.h>
#include <QtDataVisualization/q3dscene.h>
#include <QtDataVisualization/q3dcamera.h>
#include <QtDataVisualization/qbar3dseries.h>
#include <QtDataVisualization/q3dtheme.h>

#include <QtWidgets/QApplication>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QTableWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QFileDialog>
#include <QtGui/QScreen>
#include <QtCore/QRandomGenerator>
#include <QtCore/QTimer>
#include <QtCore/QFile>
#include <QtCore/QString>
#include <QtGui/QFont>
#include <QtCore/QDebug>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMessageBox>


class QtWidgets : public QObject // Declare class type QtWidgets to get access to QSaveButton
{
 public:
    explicit QtWidgets(QPushButton *save_button);
    ~QtWidgets();

    void QPushButton(); // Declare push button function to be used later for save button nimplementation
};


#define USE_STATIC_DATA
using namespace QtDataVisualization;

class GraphDataGenerator : public QObject
{
public:
    explicit GraphDataGenerator(Q3DBars *bargraph, QTableWidget *tableWidget);
    ~GraphDataGenerator();

    // Declare all classes and functions needed throught the .cpp file
    void setupModel();
    void addRow();
    void changeStyle();
    void changePresetCamera();
    void changeTheme();
    void start();
    void selectFromTable(const QPoint &selection);
    void selectedFromTable(int currentRow, int currentColumn, int previousRow, int previousColumn);
    void fixTableSize();



private:
    Q3DBars *m_graph;
    QTimer *m_dataTimer;
    int m_columnCount;
    int m_rowCount;
    QTableWidget *m_tableWidget; // not owned

};

GraphDataGenerator::GraphDataGenerator(Q3DBars *bargraph, QTableWidget *tableWidget)
    : m_graph(bargraph),
      m_dataTimer(0),
      m_columnCount(5),
      m_rowCount(10),
      m_tableWidget(tableWidget)
{
    //! [5]

    m_graph->setBarThickness(1.0f);
    m_graph->setBarSpacing(QSizeF(0.2, 0.2));

    //! [5]
#ifndef USE_STATIC_DATA
    // Set up sample space; make it as deep as it's wide
    m_graph->rowAxis()->setRange(0, m_rowCount);
    m_graph->columnAxis()->setRange(0, m_columnCount);
    m_tableWidget->setColumnCount(m_columnCount);

    // Set selection mode to full
    m_graph->setSelectionMode(QAbstract3DGraph::SelectionItemRowAndColumn);

    // Hide axis labels by explicitly setting one empty string as label list
    m_graph->rowAxis()->setLabels(QStringList(QString()));
    m_graph->columnAxis()->setLabels(QStringList(QString()));

    m_graph->seriesList().at(0)->setItemLabelFormat(QStringLiteral("@valueLabel"));
#else
    //! [6]
    // Set selection mode to slice row
    m_graph->setSelectionMode(QAbstract3DGraph::SelectionItemAndRow | QAbstract3DGraph::SelectionSlice);

    //! [6]
#endif

    //! [7]
    // Set theme
    m_graph->activeTheme()->setType(Q3DTheme::ThemeArmyBlue);

    // Set font
    m_graph->activeTheme()->setFont(QFont("Helvetica", 20));

    // Set preset camera position
    m_graph->scene()->activeCamera()->setCameraPreset(Q3DCamera::CameraPresetFront); // Eventually for real time camrea feed
    //! [7]
}

GraphDataGenerator::~GraphDataGenerator()
{
    if (m_dataTimer) {
        m_dataTimer->stop();
        delete m_dataTimer;
    }
    delete m_graph;
}

void GraphDataGenerator::start()
{
#ifndef USE_STATIC_DATA
    m_dataTimer = new QTimer();
    m_dataTimer->setTimerType(Qt::CoarseTimer);
    QObject::connect(m_dataTimer, &QTimer::timeout, this, &GraphDataGenerator::addRow);
    m_dataTimer->start(0);
    m_tableWidget->setFixedWidth(m_graph->width());
#else
    //! [8]
    setupModel();

    // Table needs to be shown before the size of its headers can be accurately obtained,
    // so we postpone it a bit
    m_dataTimer = new QTimer();
    m_dataTimer->setSingleShot(true);
    QObject::connect(m_dataTimer, &QTimer::timeout, this, &GraphDataGenerator::fixTableSize);
    m_dataTimer->start(0);
    //! [8]
#endif
}


// Save Data to .csv file - must still incorporate save button after function is finished

void QtWidgets::QPushButton()
{
QString filename = QFileDialog::getSaveFileName(QWidget *parent = nullptr, "DialogTitle", "filename.csv", "CSV files (.csv);;Zip files (.zip, *.7z)", 0, 0); // getting the filename (full path)
QFile data(filename);

if (data.open(QFile::WriteOnly|QFile::Truncate))
{
    QTextStream output(&data);
    output << "'Data has been exported!'";
}}
// End of save button section



void GraphDataGenerator::setupModel()
{
    //! [9]
    // Set up row and column names
    QStringList metric;
    metric << "X - Location" << "Y - Location" << "Z - Location" << "R - Rotation" << "X Theta" << "Y Theta" << "Z Theta";
    QStringList TimeStamp;
    TimeStamp << "1" << "2" << "3" << "4" << "5";

    // Hardcoded data for the sake of data display - must pass variable that contains actual data from HTC code
    // Set up data         X     Y     Z     R    X Theta - Y Theta - Z Theta
    float hours[5][7] = {{2.0f, 1.0f, 3.0f, 0.2f, 1.0f, 5.0f, 10.0f},     // 1
                         {0.5f, 1.0f, 3.0f, 1.0f, 2.0f, 2.0f, 3.0f},      // 2
                         {1.0f, 1.0f, 2.0f, 1.0f, 4.0f, 4.0f, 4.0f},      // 3
                         {0.0f, 1.0f, 0.0f, 0.0f, 2.0f, 2.0f, 0.3f},      // 4
                         {3.0f, 3.0f, 6.0f, 2.0f, 2.0f, 1.0f, 1.0f}};     // 5
    //! [9]

    // Add labels
    //! [10]
    m_graph->rowAxis()->setTitle("Week of Activity");
    m_graph->rowAxis()->setTitleVisible(true);
    m_graph->columnAxis()->setTitle("Coordinate Activity");
    m_graph->columnAxis()->setTitleVisible(true);
    m_graph->valueAxis()->setTitle("Average Activity");
    m_graph->valueAxis()->setTitleVisible(true);
    m_graph->valueAxis()->setLabelFormat("%.1f h");
    //! [10]

    //! [11]
    m_tableWidget->setRowCount(5);
    m_tableWidget->setColumnCount(7);
    m_tableWidget->setHorizontalHeaderLabels(metric);
    m_tableWidget->setVerticalHeaderLabels(TimeStamp);
    m_tableWidget->setHorizontalScrollBarPolicy(Qt::ScrollBarAlwaysOff);
    m_tableWidget->setVerticalScrollBarPolicy(Qt::ScrollBarAlwaysOff);
    m_tableWidget->setCurrentCell(-1, -1);
    m_tableWidget->setSelectionMode(QAbstractItemView::SingleSelection);
    //! [11]


    // Setting table size
    //! [12]
    for (int second = 0; second< TimeStamp.size(); second++) {
        for (int day = 0; day < metric.size(); day++) {
            QModelIndex index = m_tableWidget->model()->index(second, day);
            m_tableWidget->model()->setData(index, hours[second][day]);
        }
    }
    //! [12]
}

// Dynamic data population for data table - for example data
void GraphDataGenerator::addRow()
{
    m_tableWidget->model()->insertRow(0);
    if (m_tableWidget->model()->rowCount() > m_rowCount)
        m_tableWidget->model()->removeRow(m_rowCount);
    for (int i = 0; i < m_columnCount; i++) {
        QModelIndex index = m_tableWidget->model()->index(0, i);
        m_tableWidget->model()->setData(index,
            ((float)i / (float)m_columnCount) / 2.0f +
                                        (float)(QRandomGenerator::global()->bounded(30)) / 100.0f);
    }
    m_tableWidget->resizeColumnsToContents();
}

//! [13]
void GraphDataGenerator::selectFromTable(const QPoint &selection)
{
    m_tableWidget->setFocus();
    m_tableWidget->setCurrentCell(selection.x(), selection.y());
}
//! [13]

//! [14]
void GraphDataGenerator::selectedFromTable(int currentRow, int currentColumn,
                                           int previousRow, int previousColumn)
{
    Q_UNUSED(previousRow)
    Q_UNUSED(previousColumn)
    m_graph->seriesList().at(0)->setSelectedBar(QPoint(currentRow, currentColumn));
}
//! [14]

void GraphDataGenerator::fixTableSize()
{
    int width = m_tableWidget->horizontalHeader()->length();
    width += m_tableWidget->verticalHeader()->width();
    m_tableWidget->setFixedWidth(width + 2);
    int height = m_tableWidget->verticalHeader()->length();
    height += m_tableWidget->horizontalHeader()->height();
    m_tableWidget->setFixedHeight(height + 2);
}

int main(int argc, char **argv)
{
    //! [0]
    QApplication app(argc, argv);
    Q3DBars *graph = new Q3DBars();
    QWidget *container = QWidget::createWindowContainer(graph);
    //! [0]

    if (!graph->hasContext()) {
        QMessageBox msgBox;
        msgBox.setText("Couldn't initialize the OpenGL context.");
        msgBox.exec();
        return -1;
    }

    QSize screenSize = graph->screen()->size();
    container->setMinimumSize(QSize(screenSize.width() / 2, screenSize.height() / 2));
    container->setMaximumSize(screenSize);
    container->setSizePolicy(QSizePolicy::Expanding, QSizePolicy::Expanding);
    container->setFocusPolicy(Qt::StrongFocus);

    //! [1]
    QWidget widget;
    QVBoxLayout *layout = new QVBoxLayout(&widget);
    QTableWidget *tableWidget = new QTableWidget(&widget);
    layout->addWidget(container, 1);
    layout->addWidget(tableWidget, 1, Qt::AlignHCenter);
    //! [1]

    tableWidget->setSizePolicy(QSizePolicy::Expanding, QSizePolicy::Fixed);
    tableWidget->setAlternatingRowColors(true);
    widget.setWindowTitle(QStringLiteral("Average Activity"));

    //! [2]
    // Since we are dealing with QTableWidget, the model will already have data sorted properly
    // into rows and columns, so set useModelCategories property to true to utilize this.

    QItemModelBarDataProxy *proxy = new QItemModelBarDataProxy(tableWidget->model());
    proxy->setUseModelCategories(true);
    QBar3DSeries *series = new QBar3DSeries(proxy);
    series->setMesh(QAbstract3DSeries::MeshCube); // Look up QAbstract3DSeries for bar graph options
    graph->addSeries(series); // Add Bar graph style to bar graph widget
    //! [2]

    //! [3]
    GraphDataGenerator generator(graph, tableWidget);
    QObject::connect(series, &QBar3DSeries::selectedBarChanged, &generator,
                     &GraphDataGenerator::selectFromTable);
    QObject::connect(tableWidget, &QTableWidget::currentCellChanged, &generator,
                     &GraphDataGenerator::selectedFromTable);
    //! [3]

    //! [4]
    widget.show();
    generator.start();
    return app.exec();
    //! [4]
}
