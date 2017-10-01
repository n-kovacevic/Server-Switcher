using System;
using System.Collections.Generic;
using System.Xml;
using System.Windows.Forms;
using Server_Switcher.Properties;

namespace Server_Switcher
{
    public partial class MainForm : Form
    {
        private Dictionary<String, String> servers;
        public MainForm()
        {
            InitializeComponent();
            reloadServerList();
        }

        private void reloadServerList()
        {
            servers = new Dictionary<string, string>();
            XmlDocument document = new XmlDocument();
            document.Load("config.xml");
            XmlNodeList nodeList = document.GetElementsByTagName("server");
            serverCombo.Items.Clear();
            foreach (XmlNode node in nodeList)
            {
                servers.Add(node.Attributes["name"].Value, node.InnerText);
            }
        }

        private void refreshServerCombo()
        {
            serverCombo.Items.Clear();
            foreach(string key in servers.Keys)
            {
                serverCombo.Items.Add(key);
            }
            serverCombo.SelectedItem = serverCombo.Items[0];
        }

        private void startGame_MouseEnter(object sender, EventArgs e)
        {
            startGame.Image = Resources.play_hover;
        }


        private void startGame_MouseLeave(object sender, EventArgs e)
        {
            startGame.Image = Resources.play_default;
        }

        private void startGame_MouseDown(object sender, MouseEventArgs e)
        {
            startGame.Image = Resources.play_active;
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            refreshServerCombo();
        }
    }
}
