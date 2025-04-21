//
//  AboutScreen.swift
//  iosApp
//
//  Created by Rapha on 20/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct AboutScreen: View {
    var body: some View {
        NavigationStack {
            AboutView()
                .navigationTitle("About Device")
        }
    }
}
